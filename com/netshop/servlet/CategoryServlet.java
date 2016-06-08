package com.netshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.model.Category;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.ItemsServiceImpl;

/**
 * Servlet implementation class CategoryServlet
 */

@SuppressWarnings("serial")
public class CategoryServlet extends BaseServlet {
	
    
	private ItemsService itemsService = new ItemsServiceImpl();	
	
	/**
	 * 查询所有分类
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 通过service得到所有的分类
		 * 2. 保存到request中，转发到left.jsp
		 */
		List<Category> parents = itemsService.loadByType();
		//req.setAttribute("parents1", parents);
		req.setAttribute("parents2", parents);
		return "f:/jsps/left.jsp";
	}
	
}
