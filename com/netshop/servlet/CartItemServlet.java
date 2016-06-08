package com.netshop.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.commons.CommonUtils;
import com.netshop.model.CartItem;
import com.netshop.model.Items;
import com.netshop.model.User;
import com.netshop.service.CartItemService;
import com.netshop.service.implement.CartItemServiceImpl;

@SuppressWarnings("serial")
public class CartItemServlet extends BaseServlet {
	private CartItemService cartItemService = new CartItemServiceImpl();

	/**
	 * ���ض��CartItem
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadCartItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ��ȡcartItemIds����
		String cartItemIds = req.getParameter("cartItemIds");
		double total = Double.parseDouble(req.getParameter("total"));

		// ͨ��service�õ�List<CartItem>
		List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);

		// ���棬Ȼ��ת����/cart/showitem.jsp
		req.setAttribute("cartItemList", cartItemList);
		req.setAttribute("total", total);
		req.setAttribute("cartItemIds", cartItemIds);
		return "f:/jsps/cart/showitem.jsp";
	}

	//��������
	public String updateQuantity(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cartItemId = req.getParameter("cartItemId");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		CartItem criteria = cartItemService.updateQuantity(cartItemId, quantity);

		// ���ͻ��˷���һ��json����
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"quantity\"").append(":").append(criteria.getQuantity());
		sb.append(",");
		sb.append("\"subtotal\"").append(":").append(criteria.getSubtotal());
		sb.append("}");

		resp.getWriter().print(sb);
		return null;
	}

	/**
	 * ����ɾ������
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ��ȡcartItemIds���� 2. ����service������ɹ��� 3. ���ص�list.jsp
		String cartItemIds = req.getParameter("cartItemIds");
		cartItemService.Delete(cartItemIds);
		return myCart(req, resp);
	}

	/**
	 * ��ӹ��ﳵ��Ŀ
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ��װ�����ݵ�CartItem(bid, quantity)
		/*Map map = req.getParameterMap();
		CartItem cartitem = CommonUtils.toBean(map, CartItem.class);
		Items item = CommonUtils.toBean(map, Items.class);
		User user = (User) req.getSession().getAttribute("usersession");
		cartitem.setItems(item);
		cartitem.setUser(user);*/
		
		/*
		 * ���ﰴ����Ŀ���ṩ�ķ���ע���ǲ��еģ�������Ҫͨ��getparameter����ò���
		 * Ȼ���ٽ�������ӽ�ȥ
		 * ����Ϊʲô��������ķ����Ӳ���ȥ����Ҳ��֪����������������
		 */
		Map map=req.getParameterMap();
		int  id=Integer.parseInt(req.getParameter("bid"));
		int  quantity=Integer.parseInt(req.getParameter("quantity"));
		CartItem cartitem = CommonUtils.toBean(map, CartItem.class);
		Items item = CommonUtils.toBean(map, Items.class);
		User user = (User) req.getSession().getAttribute("usersession");
		
		//�����û��Ƿ��½
		if(user == null)
		{
			return "f:/jsps/msg.jsp";
		}
		
		if(cartitem.getQuantity()!=0)
		{
			cartitem.setQuantity(quantity);
		}
		cartitem.setItems(item);
		cartitem.getItems().setItem_id(id);
		cartitem.setUser(user);
		
		// ����service������
		cartItemService.add(cartitem);
		
		// ��ѯ����ǰ�û���������Ŀ��ת����list.jsp��ʾ
		return myCart(req, resp);
	}

	/**
	 * ���ﳵ
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// �õ�uid
		User user = (User) req.getSession().getAttribute("usersession");
		int uid = user.getU_id();

		// ͨ��service�õ���ǰ�û������й��ﳵ��Ŀ
		List<CartItem> cartItemLIst = cartItemService.myCart(uid);

		// ����������ת����/cart/list.jsp
		req.setAttribute("cartItemList", cartItemLIst);
		return "f:/jsps/cart/list.jsp";
	}
}
