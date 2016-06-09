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
 * Servlet implementation class InProServlet
 */
@WebServlet("/InProServlet")
public class InProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	ControlDao controlDao=new ControlDaoImpl();
	ItemExcelDao itemExcelDao=new ItemExcelDaoImpl();
	String result;
    public InProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String filePath = this.getServletContext().getRealPath("/Excel/in.xls");
		
		try {
			List<Items> items=controlDao.readXlsCome(filePath);
			for(Items ex:items)
			{
				itemExcelDao.sub(ex);
				if(itemExcelDao.checkQuery(ex.getBarcode()))
				{
					Items newex=new Items();
					newex=itemExcelDao.findStock(ex.getBarcode());
					//num1是要入库的数量
					int num1=Integer.valueOf(ex.getItem_stock());
					//num2是库中的数量
					int num2=Integer.valueOf(newex.getItem_stock());
					//num3是相加之后要入库的新数量
					int num3=num1+num2;
					//字符型的总数量，赋值给ex
					String num=String.valueOf(num3);
					//将新的库存数量赋值给ex
					ex.setItem_stock(num);
					itemExcelDao.update(ex);
				}
				else
				{
					itemExcelDao.insert(ex);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result="执行成功";
		req.setAttribute("result2", result);
		req.getRequestDispatcher("/admin/inStorage.jsp").forward(req, resp);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
