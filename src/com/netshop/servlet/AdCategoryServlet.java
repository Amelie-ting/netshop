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
	
	
	/**
	 * 添加一级分类
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
		 * 1. 封装表单数据到Category中
		 * 2. 调用service的add()方法完成添加
		 * 3. 调用findAll()，返回list.jsp显示所有分类
		 */
		Category parent = CommonUtils.toBean(req.getParameterMap(), Category.class);
		//parent.setCid(CommonUtils.uuid());//设置cid
		parent.setCa_id(CommonUtils.uuid());
		categoryService.add(parent);
		return findAll(req, resp);
	}
	
	/**
	 * 修改一级分类：第一步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editParentPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取链接中的cid
		 * 2. 使用cid加载Category
		 * 3. 保存Category
		 * 4. 转发到edit.jsp页面显示Category
		 */
		String cid = req.getParameter("cid");
		Category parent = categoryService.load(cid);
		req.setAttribute("parent", parent);
		return "f:/adminjsps/admin/category/edit.jsp";
	}
	
	/**
	 * 修改一级分类：第二步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editParent(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Category中
		 * 2. 调用service方法完成修改
		 * 3. 转发到list.jsp显示所有分类（return findAll();）
		 */
		Category parent = CommonUtils.toBean(req.getParameterMap(), Category.class);
		categoryService.edit(parent);
		return findAll(req, resp);
		
	}
	
	/**
	 * 修改二级分类：第一步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editChildPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取链接参数cid，通过cid加载Category，保存之
		 * 2. 查询出所有1级分类，保存之
		 * 3. 转发到edit2.jsp
		 */
		String cid = req.getParameter("cid");
		Category child = categoryService.load(cid);
		req.setAttribute("child", child);
		req.setAttribute("parents", categoryService.findParents());
		
		return "f:/adminjsps/admin/category/edit2.jsp";
	}
	
	/**
	 * 修改二级分类：第二步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editChild(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单参数到Category child
		 * 2. 把表单中的pid封装到child, ...
		 * 3. 调用service.edit()完成修改
		 * 4. 返回到list.jsp
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
