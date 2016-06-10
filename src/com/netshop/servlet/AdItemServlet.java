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
		/*
		 * 1. �õ�pc�����ҳ�洫�ݣ�ʹ��ҳ��ģ����û����pc=1
		 */
		// int pc = getPc(req);
		// /*
		// * 2. �õ�url��...
		// */
		// String url = getUrl(req);
		/*
		 * 3. ��ȡ��ѯ����������������cid���������id
		 */
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

		/*
		 * 1. �õ�pc�����ҳ�洫�ݣ�ʹ��ҳ��ģ����û����pc=1
		 */
		// int pc = getPc(req);
		// /*
		// * 2. �õ�url��...
		// */
		// String url = getUrl(req);
		/*
		 * 3. ��ȡ��ѯ����������������cid���������id
		 */
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

}
