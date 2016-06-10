package com.netshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.model.Category;
import com.netshop.service.CategoryService;
import com.netshop.service.implement.CategoryServiceImpl;

/**
 * Servlet implementation class AdCategoryServlet
 */
@WebServlet("/AdCategoryServlet")
public class AdCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	private CategoryService categoryService = new CategoryServiceImpl();

	/**
	 * 查询所有分类
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1. 通过service得到所有的分类 2. 保存到request中，转发到left.jsp
		 */
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "f:/adminjsps/admin/category/list.jsp";

	}

}
