package com.netshop.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.commons.CommonUtils;
import com.netshop.model.Category;
import com.netshop.model.CriteriaItems;
import com.netshop.model.ItemBar;
import com.netshop.model.Items;
import com.netshop.service.CategoryService;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.CategoryServiceImpl;
import com.netshop.service.implement.ItemsServiceImpl;

/**
 * Servlet implementation class AdminItemServlet
 */
@WebServlet("/AdminItemServlet")
public class AdminItemServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private ItemsService itemsService = new ItemsServiceImpl();
	private CategoryService categoryService=new CategoryServiceImpl();
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
	 * 商品的编辑功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 把表单数据封装到Book对象中
		 * 2. 封装cid到Category中
		 * 3. 把Category赋给Book
		 * 4. 调用service完成工作
		 * 5. 保存成功信息，转发到msg.jsp
		 */
		Map map = req.getParameterMap();
		Items items=CommonUtils.toBean(map, Items.class);
		Category category = CommonUtils.toBean(map, Category.class);
		items.setCategory(category);
		itemsService.edit(items);
		req.setAttribute("msg", "修改图书成功！");
		return "f:/adminjsps/msg.jsp";
	}
	
	/**
	 * 加载图书
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取bid，得到Book对象，保存之
		 */
		String bid = req.getParameter("bid");
		int id = Integer.parseInt(bid);
		Items items=itemsService.loadByIid(id);
		req.setAttribute("item", items);
		
		/*
		 * 2. 获取所有一级分类，保存之
		 */
		req.setAttribute("parents", categoryService.findParents());
		/*
		 * 3. 获取当前图书所属的一级分类下所有2级分类
		 */
		String pid =items.getCategory().getParent().getCa_id();
		//System.out.println(items.getItem_name());
		req.setAttribute("children", categoryService.findChildren(pid));
		
		/*
		 * 4. 转发到desc.jsp显示
		 */
		return "f:/adminjsps/admin/book/desc.jsp";
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
		//System.out.println(json);
		return null;
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
	
	public String delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String bid = req.getParameter("item_id");
		
		int id=Integer.valueOf(bid);
		/*
		 * 删除图片
		 */

		Items items=itemsService.loadByIid(id);
		String savepath = this.getServletContext().getRealPath("/");//获取真实的路径
		new File(savepath, items.getItem_pic()).delete();//删除文件
		itemsService.delete(bid);
		
		req.setAttribute("msg", "删除图书成功！");
		return "f:/adminjsps/msg.jsp";
	}
	
	public String findnull(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		List<Items> items=itemsService.findnull();
		req.setAttribute("nullItems", items);
		return "f:/adminjsps/admin/book/findnull.jsp";
	}
	
	public String findByItemBar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		String bid = req.getParameter("bid");
		int id=Integer.valueOf(bid);
		ItemBar itemBar=itemsService.findByItemBar(id);
		req.setAttribute("nullItems", itemBar);
		List<Category> parents = categoryService.findParents();
		req.setAttribute("parents", parents);
		//删除这个item
		
		return "f:/adminjsps/admin/book/addEdit.jsp";
	}
}
