package com.netshop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.dao.ControlDao;
import com.netshop.dao.implement.ControlDaoImpl;
import com.netshop.model.Items;

/**
 * Servlet implementation class ExcelProcessing
 */
@WebServlet("/ExcelProcessing")
public class ExcelProcessing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	ControlDao controlDao=new ControlDaoImpl();
    public ExcelProcessing() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String filePath = this.getServletContext().getRealPath("/Excel/excel.xls");
		
		try {
			List<Items> items=controlDao.readXlsCome(filePath);
			for (Items newitem:items) {
				String start1=newitem.getItem_stock();
				String sub1=start1.substring(0,start1.length()-2);
				String start2=newitem.getBarcode();
				String sub2=start2.substring(0,start2.length()-2);
				System.out.println(sub1+" "+sub2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
