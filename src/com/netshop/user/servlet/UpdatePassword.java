package com.netshop.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.model.User;
import com.netshop.service.UserService;
import com.netshop.service.implement.UserServiceImpl;

/**
 * Servlet implementation class UpdatePassword
 */
@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.ͨ��getsession���session��ֵ��ע�ⷵ�ص���user���� 2.���ǰ̨�����������룬���бȽ� 3.ʹ�÷�����Ȼ�󷵻�
		 */
		User user = (User) request.getSession().getAttribute("usersession");
		String old = request.getParameter("old");
		String new1 = request.getParameter("new1");
		String new2 = request.getParameter("new2");
		UserService userService = new UserServiceImpl();
		
		if (user == null) {
			request.setAttribute("msg", "����û�е�¼����");
			request.getRequestDispatcher("jsps/user/login.jsp").forward(request, response);

		}
		if (!new1.equals(new2)) {
			request.setAttribute("msg", "�����������벻һ��");
			request.getRequestDispatcher("jsps/user/pwd.jsp").forward(request, response);
		}
		if(old!=user.getU_password()){
			request.setAttribute("msg", "�����벻��ȷ�����������룡");
			request.getRequestDispatcher("jsps/user/login.jsp").forward(request, response);
		}else {
			try {
				userService.updatePassword(user.getU_id(), new1, old);
				request.setAttribute("msg", "�޸�����ɹ���");
				request.getRequestDispatcher("jsps/user/pwd.jsp").forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
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
