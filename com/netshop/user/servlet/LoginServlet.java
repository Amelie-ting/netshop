package com.netshop.user.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.dao.UserDao;
import com.netshop.dao.implement.UserDaoImpl;
import com.netshop.model.User;
import com.netshop.service.UserService;
import com.netshop.service.implement.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��session�л�ȡ��֤����Ϣ
		String piccode = (String) request.getSession().getAttribute("piccode");
		//��ȡ��֤���������֤����Ϣ
		String checkcode = request.getParameter("code");
		//��֤���Сдת��
		checkcode = checkcode.toUpperCase();
		
		User user=new User();
		UserService userService=new UserServiceImpl();
		RequestDispatcher rd=null;  //��ҳָ��
		String name=request.getParameter("name");
		String upwd=request.getParameter("password");
		user.setU_name(name);
		user.setU_password(upwd);
		String forward =null;
		UserDao ud=new UserDaoImpl();
		if(userService.login(user)!=null)
		{			
			if(checkcode.equals(piccode))
			{
				try {
					user=ud.findByLoginnameAndnpass(name, upwd);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("usersession", user);
				response.sendRedirect("index.jsp");  
	            return ;
			}
			else {
				request.setAttribute("msg", "��֤���������");
				request.getRequestDispatcher("jsps/user/login.jsp").forward(request,response); 
			}
		}
		else 
		{
			request.setAttribute("msg", "�����������");
//			rd=request.getRequestDispatcher("User/Login.jsp");
//			rd.forward(request, response);
			request.getRequestDispatcher("jsps/user/login.jsp").forward(request,response); 
			//System.out.println("no");
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
