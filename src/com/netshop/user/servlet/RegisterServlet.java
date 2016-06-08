package com.netshop.user.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.model.User;
import com.netshop.service.UserService;
import com.netshop.service.implement.UserServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService cku = new UserServiceImpl();
		RequestDispatcher rd = null;
		User user = new User();
		String name = request.getParameter("userid");
		String pas1 = request.getParameter("password1");
		String pas2 = request.getParameter("password2");
		String phone = request.getParameter("phone");
		if (!cku.ajaxValidateLoginname(name)) {
			request.setAttribute("msg", "用户名已经被使用");
			rd = request.getRequestDispatcher("jsps/user/regist.jsp");
			rd.forward(request, response);
			return;
		}
		if (!pas1.equals(pas2)) {
			request.setAttribute("msg", "两次密码输入不一致！");
			rd = request.getRequestDispatcher("jsps/user/regist.jsp");
			rd.forward(request, response);
			return;
		}
		user.setU_name(name);
		user.setU_password(pas1);
		user.setU_nickname(phone);
		cku.regist(user);
		String forward = null;
		request.setAttribute("msg", "恭喜您，注册成功");
		rd = request.getRequestDispatcher("jsps/user/regist.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
