package com.netshop.servlet.Excel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.dao.ControlDao;
import com.netshop.dao.ItemExcelDao;
import com.netshop.dao.implement.ControlDaoImpl;
import com.netshop.dao.implement.ItemExcelDaoImpl;
import com.netshop.model.Items;

/**
 * Servlet implementation class OutProServlet
 */
@WebServlet("/OutProServlet")
public class OutProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ControlDao controlDao=new ControlDaoImpl();
	ItemExcelDao itemExcelDao=new ItemExcelDaoImpl();
	String result;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filePath = this.getServletContext().getRealPath("/Excel/out.xls");
		
		try {
			List<Items> items=controlDao.readXlsCome(filePath);
			for(Items ex:items)
			{
				itemExcelDao.sub(ex);
				//newex用来获得库中的数量
				Items newex= new Items();
				newex=itemExcelDao.findStock(ex.getBarcode());
				
				//num1是要出库的数量
				int num1=Integer.valueOf(ex.getItem_stock());
				//num2是库中的数量
				int num2=Integer.valueOf(newex.getItem_stock());
				//num3是相减之后库中应该有的数量
				int num3=num2-num1;
				if(num3<0)
				{
					String name=ex.getBarcode();
					result="执行失败出库数量有错误，"+"错误商品条形码为:"+name;
					req.setAttribute("result2", result);
					req.getRequestDispatcher("/adminjsps/admin/storage/storage.jsp").forward(req, resp);
					return ;
				}
				//字符型的总数量，赋值给ex
				String num=String.valueOf(num3);
				//将新的库存数量赋值给ex
				ex.setItem_stock(num);
				itemExcelDao.update(ex);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result="执行成功";
		req.setAttribute("result3", result);
		req.getRequestDispatcher("/adminjsps/admin/storage/storage.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
