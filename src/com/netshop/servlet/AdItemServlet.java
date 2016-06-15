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
	 * �����ѯ��ʾ���������Ǵ˷�����ʱ��û�н��з�ҳ
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
		 * 4. ʹ��pc��cid����service#findByCategory�õ�PageBean
		 */
		List<Items> pb = itemsService.findByCategory(id);
		/*
		 * 5. ��PageBean����url������PageBean��ת����/jsps/book/list.jsp
		 */
		// pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}

	/**
	 * �������ƽ���������ѯ
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
		 * 3.5 ʵ����criteriaitems,ֱ�Ӵ���bname
		 */
		CriteriaItems cr = new CriteriaItems(bname);

		/*
		 * 4. ʹ��pc��cid����service#findByCategory�õ�PageBean
		 */
		List<Items> pb = itemsService.Fuzzyquery(cr);
		/*
		 * 5. ��PageBean����url������PageBean��ת����/jsps/items/list.jsp
		 */

		req.setAttribute("pb", pb);

		return "f:/adminsjsps/admin/book/list.jsp";
	}

	/**
	 * ����id��������
	 * 
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bid = req.getParameter("bid");// ��ȡ���ӵĲ���bid
		int id = Integer.parseInt(bid);
		Items items = itemsService.loadByIid(id);// ͨ��bid�õ�items����
		req.setAttribute("item", items);// ���浽req��
		return "f:/adminjsps/admin/book/desc.jsp";// ת����desc.jsp
	}
	
	
	/**
	 * ���ͼ�飺��һ��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��ȡ����һ�����࣬����֮
		 * 2. ת����add.jsp����ҳ����������б�����ʾ����һ������
		 */
		List<Category> parents = categoryService.findParents();
		req.setAttribute("parents", parents);
		return "f:/adminjsps/admin/book/add.jsp";
	}
	
	public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��ȡpid
		 * 2. ͨ��pid��ѯ������2������
		 * 3. ��List<Category>ת����json��������ͻ���
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
