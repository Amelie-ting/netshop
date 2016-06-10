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
	
	/**
	 * �޸�һ�����ࣺ��һ��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editParentPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��ȡ�����е�cid
		 * 2. ʹ��cid����Category
		 * 3. ����Category
		 * 4. ת����edit.jspҳ����ʾCategory
		 */
		String cid = req.getParameter("cid");
		Category parent = categoryService.load(cid);
		req.setAttribute("parent", parent);
		return "f:/adminjsps/admin/category/edit.jsp";
	}
	
	/**
	 * �޸�һ�����ࣺ�ڶ���
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editParent(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��װ�����ݵ�Category��
		 * 2. ����service��������޸�
		 * 3. ת����list.jsp��ʾ���з��ࣨreturn findAll();��
		 */
		Category parent = CommonUtils.toBean(req.getParameterMap(), Category.class);
		categoryService.edit(parent);
		return findAll(req, resp);
		
	}
	
	/**
	 * �޸Ķ������ࣺ��һ��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editChildPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��ȡ���Ӳ���cid��ͨ��cid����Category������֮
		 * 2. ��ѯ������1�����࣬����֮
		 * 3. ת����edit2.jsp
		 */
		String cid = req.getParameter("cid");
		Category child = categoryService.load(cid);
		req.setAttribute("child", child);
		req.setAttribute("parents", categoryService.findParents());
		
		return "f:/adminjsps/admin/category/edit2.jsp";
	}
	
	/**
	 * �޸Ķ������ࣺ�ڶ���
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editChild(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��װ��������Category child
		 * 2. �ѱ��е�pid��װ��child, ...
		 * 3. ����service.edit()����޸�
		 * 4. ���ص�list.jsp
		 */
		Category child = CommonUtils.toBean(req.getParameterMap(), Category.class);
		String pid = req.getParameter("pid");
		Category parent = new Category();
		parent.setCa_id(pid);
		child.setParent(parent);
		categoryService.edit(child);
		return findAll(req, resp);
	}
	

}
