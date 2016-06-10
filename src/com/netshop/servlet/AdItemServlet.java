package com.netshop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.ItemsServiceImpl;

/**
 * Servlet implementation class AdItemServlet
 */
@WebServlet("/AdItemServlet")
public class AdItemServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	ItemsService itemsService = new ItemsServiceImpl();
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
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		// int pc = getPc(req);
		// /*
		// * 2. 得到url：...
		// */
		// String url = getUrl(req);
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
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

		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		// int pc = getPc(req);
		// /*
		// * 2. 得到url：...
		// */
		// String url = getUrl(req);
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
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

}
