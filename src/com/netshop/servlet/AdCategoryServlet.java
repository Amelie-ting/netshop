package com.netshop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.commons.CommonUtils;
import com.netshop.dao.CategoryDao;
import com.netshop.dao.implement.CategoryDaoImpl;
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
	
	private CategoryDao categoryDao=new CategoryDaoImpl();

	/**
	 * ��ѯ���з���
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1. ͨ��service�õ����еķ��� 2. ���浽request�У�ת����left.jsp
		 */
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "f:/adminjsps/admin/category/list.jsp";

	}
	
	
	/**
	 * ���һ������
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public String addParent(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		/*
		 * 1. ��װ�����ݵ�Category��
		 * 2. ����service��add()����������
		 * 3. ����findAll()������list.jsp��ʾ���з���
		 */
		Category parent = CommonUtils.toBean(req.getParameterMap(), Category.class);
		//parent.setCid(CommonUtils.uuid());//����cid
		parent.setCa_id(CommonUtils.uuid());
		categoryService.add(parent);
		return findAll(req, resp);
	}

}
