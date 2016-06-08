package com.netshop.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.model.Category;

import com.netshop.service.CategoryService;
import com.netshop.service.implement.CategoryServiceImpl;

import cn.itcast.servlet.BaseServlet;

@SuppressWarnings("serial")
public class AdminItemsServlet extends BaseServlet {

	private CategoryService categoryService = new CategoryServiceImpl();

	public String addPre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Category> parents = categoryService.findParents();
		req.setAttribute("parents", parents);
		return "f:/app/add.jsp";
	}

	public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String pid = req.getParameter("ca_pid");
		List<Category> children = categoryService.findChildren(pid);
		String json = toJson(children);

		resp.getWriter().print(json);
		return null;
	}

	private String toJson(Category category) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"ca_id\"").append(":").append("\"").append(category.getCa_id()).append("\"");
		sb.append(",");
		sb.append("\"ca_name\"").append(":").append("\"").append(category.getCa_name()).append("\"");
		sb.append("}");
		return sb.toString();
	}

	private String toJson(List<Category> categoryList) {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < categoryList.size(); i++) {
			sb.append(toJson(categoryList.get(i)));
			if (i < categoryList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
