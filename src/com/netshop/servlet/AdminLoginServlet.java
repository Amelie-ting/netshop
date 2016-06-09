package com.netshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.dao.AdminLoginDao;
import com.netshop.dao.implement.AdminLoginDaoImpl;
import com.netshop.model.Admin;
import com.netshop.service.AdminService;
import com.netshop.service.implement.AdminServiceImpl;

import cn.itcast.commons.CommonUtils;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	AdminService adminService=new AdminServiceImpl();
	public String login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Admin
		 */
		Admin form = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		Admin admin = adminService.login(form);
			if(admin == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			return "/adminjsps/login.jsp";
		}
		req.getSession().setAttribute("admin", admin);
		return "r:/adminjsps/admin/index.jsp";
	}


}
