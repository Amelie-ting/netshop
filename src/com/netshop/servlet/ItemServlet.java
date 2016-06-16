package com.netshop.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.ItemsServiceImpl;

/**
 * Servlet implementation class ItemServlet
 */

public class ItemServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ItemsService itemsService = new ItemsServiceImpl();

	/**
	 * ��ȡ��ǰҳ��
	 * 
	 * @param req
	 * @return
	 */
	// private int getPc(HttpServletRequest req) {
	// int pc = 1;
	// String param = req.getParameter("pc");
	// if(param != null && !param.trim().isEmpty()) {
	// try {
	// pc = Integer.parseInt(param);
	// } catch(RuntimeException e) {}
	// }
	// return pc;
	// }
	//
	//
	// /**
	// * ��ȡurl��ҳ���еķ�ҳ��������Ҫʹ������Ϊ�����ӵ�Ŀ�꣡
	// * @param req
	// * @return
	// */
	// /*
	// *
	// http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
	// * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
	// */
	// private String getUrl(HttpServletRequest req) {
	// String url = req.getRequestURI() + "?" + req.getQueryString();
	// /*
	// * ���url�д���pc��������ȡ��������������ǾͲ��ý�ȡ��
	// */
	// int index = url.lastIndexOf("&pc=");
	// if(index != -1) {
	// url = url.substring(0, index);
	// }
	// return url;
	// }
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

	
		/*
		 * 4. ʹ��pc��cid����service#findByCategory�õ�PageBean
		 */
		List<Items> pb = itemsService.findByCategory(ca_id);
		/*
		 * 5. ��PageBean����url������PageBean��ת����/jsps/book/list.jsp
		 */
		// pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/items/list.jsp";
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

		return "f:/jsps/items/list.jsp";
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
		return "f:/jsps/items/desc.jsp";// ת����desc.jsp
	}

}
