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
				//newex������ÿ��е�����
				Items newex= new Items();
				newex=itemExcelDao.findStock(ex.getBarcode());
				
				//num1��Ҫ���������
				int num1=Integer.valueOf(ex.getItem_stock());
				//num2�ǿ��е�����
				int num2=Integer.valueOf(newex.getItem_stock());
				//num3�����֮�����Ӧ���е�����
				int num3=num2-num1;
				if(num3<0)
				{
					String name=ex.getBarcode();
					result="ִ��ʧ�ܳ��������д���"+"������Ʒ������Ϊ:"+name;
					req.setAttribute("result2", result);
					req.getRequestDispatcher("/adminjsps/admin/storage/storage.jsp").forward(req, resp);
					return ;
				}
				//�ַ��͵�����������ֵ��ex
				String num=String.valueOf(num3);
				//���µĿ��������ֵ��ex
				ex.setItem_stock(num);
				itemExcelDao.update(ex);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result="ִ�гɹ�";
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
