package com.netshop.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.ItemsServiceImpl;

/**
 * 专门用来写后台搜索的
 */
@WebServlet("/AdminSearchServlet")
public class AdminSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	ItemsService itemsService=new ItemsServiceImpl();
	
    public AdminSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		System.out.println(name);
		if(isNum(name)){
			Items items=itemsService.findItemByBarcode(name);
			req.setAttribute("pb",items);
			req.getRequestDispatcher("adminjsps/admin/book/list.jsp").forward(req, resp);
		}
		else{
			CriteriaItems cr = new CriteriaItems(name);
			List<Items> pb = itemsService.Fuzzyquery(cr);
			req.setAttribute("pb", pb);
			req.getRequestDispatcher("adminjsps/admin/book/list.jsp").forward(req, resp);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	/**
	 * 正则表达式，匹配条形码全为数字
	 * @param str
	 * @return
	 */
	private static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

}
