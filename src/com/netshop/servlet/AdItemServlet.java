package com.netshop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.netshop.dao.ItemsDao;
import com.netshop.dao.implement.ItemsDaoImpl;
import com.netshop.model.Category;
import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.service.CategoryService;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.CategoryServiceImpl;
import com.netshop.service.implement.ItemsServiceImpl;

/**
 * Servlet implementation class AdItemServlet
 */
@WebServlet("/AdItemServlet")
public class AdItemServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ItemsService itemsService = new ItemsServiceImpl();
	private CategoryService categoryService=new CategoryServiceImpl();
	private ItemsDao itemsDao=new ItemsDaoImpl();
	/**
	 * 分类查询显示方法，但是此方法暂时还没有进行分页
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String ca_id = req.getParameter("ca_id");

		int id = Integer.parseInt(ca_id);
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		List<Items> pb = itemsService.findByCategory(id);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		// pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}

	/**
	 * 按照名称进行搜索查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */

	public String findByBname(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {

		String bname = req.getParameter("bname");
		/*
		 * 3.5 实例化criteriaitems,直接传入bname
		 */
		CriteriaItems cr = new CriteriaItems(bname);

		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		List<Items> pb = itemsService.Fuzzyquery(cr);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/items/list.jsp
		 */

		req.setAttribute("pb", pb);

		return "f:/adminsjsps/admin/book/list.jsp";
	}

	/**
	 * 按照id进行载入
	 * 
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bid = req.getParameter("bid");// 获取链接的参数bid
		int id = Integer.parseInt(bid);
		Items items = itemsService.loadByIid(id);// 通过bid得到items对象
		req.setAttribute("item", items);// 保存到req中
		return "f:/adminjsps/admin/book/desc.jsp";// 转发到desc.jsp
	}
	
	
	/**
	 * 添加图书：第一步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取所有一级分类，保存之
		 * 2. 转发到add.jsp，该页面会在下拉列表中显示所有一级分类
		 */
		List<Category> parents = categoryService.findParents();
		req.setAttribute("parents", parents);
		return "f:/adminjsps/admin/book/add.jsp";
	}
	
	public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取pid
		 * 2. 通过pid查询出所有2级分类
		 * 3. 把List<Category>转换成json，输出给客户端
		 */
		String pid = req.getParameter("pid");
		List<Category> children = categoryService.findChildren(pid);
		String json = toJson(children);
		resp.getWriter().print(json);
		System.out.println(json);
		return json;
	}
	
	
	
	// {"cid":"fdsafdsa", "cname":"fdsafdas"}
	private String toJson(Category category) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"cid\"").append(":").append("\"").append(category.getCa_id()).append("\"");
		sb.append(",");
		sb.append("\"cname\"").append(":").append("\"").append(category.getCa_name()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	// [{"cid":"fdsafdsa", "cname":"fdsafdas"}, {"cid":"fdsafdsa", "cname":"fdsafdas"}]
	private String toJson(List<Category> categoryList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < categoryList.size(); i++) {
			sb.append(toJson(categoryList.get(i)));
			if(i < categoryList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	private String findnull(HttpServletRequest req, HttpServletResponse resp){
		List<Items> items=itemsService.findnull();
		req.setAttribute("nullItems", items);
		return "f:/adminjsps/admin/book/findnull.jsp";
	}

}
