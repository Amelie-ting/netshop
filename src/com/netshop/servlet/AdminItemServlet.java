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
	 * ��Ʒ�ı༭����
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. �ѱ����ݷ�װ��Book������
		 * 2. ��װcid��Category��
		 * 3. ��Category����Book
		 * 4. ����service��ɹ���
		 * 5. ����ɹ���Ϣ��ת����msg.jsp
		 */
		Map map = req.getParameterMap();
		Items items=CommonUtils.toBean(map, Items.class);
		Category category = CommonUtils.toBean(map, Category.class);
		items.setCategory(category);
		itemsService.edit(items);
		req.setAttribute("msg", "�޸�ͼ��ɹ���");
		return "f:/adminjsps/msg.jsp";
	}
	
	/**
	 * ����ͼ��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��ȡbid���õ�Book���󣬱���֮
		 */
		String bid = req.getParameter("bid");
		int id = Integer.parseInt(bid);
		Items items=itemsService.loadByIid(id);
		req.setAttribute("item", items);
		
		/*
		 * 2. ��ȡ����һ�����࣬����֮
		 */
		req.setAttribute("parents", categoryService.findParents());
		/*
		 * 3. ��ȡ��ǰͼ��������һ������������2������
		 */
		String pid =items.getCategory().getParent().getCa_id();
		//System.out.println(items.getItem_name());
		req.setAttribute("children", categoryService.findChildren(pid));
		
		/*
		 * 4. ת����desc.jsp��ʾ
		 */
		return "f:/adminjsps/admin/book/desc.jsp";
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
		 * ɾ��ͼƬ
		 */

		Items items=itemsService.loadByIid(id);
		String savepath = this.getServletContext().getRealPath("/");//��ȡ��ʵ��·��
		new File(savepath, items.getItem_pic()).delete();//ɾ���ļ�
		itemsService.delete(bid);
		
		req.setAttribute("msg", "ɾ��ͼ��ɹ���");
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
		//ɾ�����item
		
		return "f:/adminjsps/admin/book/addEdit.jsp";
	}
}
