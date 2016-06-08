package com.netshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.model.Category;
import com.netshop.service.CategoryService;

import com.netshop.service.implement.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryServlet
 */

@SuppressWarnings("serial")
public class CategoryServlet extends BaseServlet {

	private CategoryService categoryService = new CategoryServiceImpl();

	/**
	 * ��ѯ���з���
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1. ͨ��service�õ����еķ��� 2. ���浽request�У�ת����left.jsp
		 */
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "f:/jsps/left.jsp";

	}

}
