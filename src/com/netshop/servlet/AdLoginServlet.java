package com.netshop.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.dao.AdLoginDao;

import com.netshop.dao.UserDao;
import com.netshop.dao.implement.AdLoginDaoImpl;

import com.netshop.dao.implement.UserDaoImpl;
import com.netshop.model.Admin;
import com.netshop.model.User;
import com.netshop.service.UserService;
import com.netshop.service.implement.UserServiceImpl;

/**
 * Servlet implementation class AdLoginServlet
 */
@WebServlet("/AdLoginServlet")
public class AdLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Admin admin= new Admin();
		
		AdLoginDao adLoginDao=new AdLoginDaoImpl();
	
		RequestDispatcher rd = null; // Õ¯“≥÷∏œÚ
		String name = request.getParameter("name");
		String upwd = request.getParameter("password");
		String forward = null;
		
		try {
			admin=adLoginDao.find(name, upwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(admin!=null)
		{
			request.getSession().setAttribute("admin", admin);
			response.sendRedirect("adminjsps/admin/main.jsp");
			return;
		}
		else
		{
			request.setAttribute("msg", "√‹¬Î ‰»Î¥ÌŒÛ");
			request.getRequestDispatcher("adminjsps/login.jsp").forward(request, response);
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
