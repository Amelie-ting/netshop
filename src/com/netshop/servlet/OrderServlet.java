package com.netshop.servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.commons.CommonUtils;
import com.netshop.model.CartItem;

import com.netshop.model.Order;
import com.netshop.model.OrderItem;
import com.netshop.model.User;
import com.netshop.model.UserAddress;
import com.netshop.pager.PageBean;

import com.netshop.service.CartItemService;
import com.netshop.service.OrderService;

import com.netshop.service.implement.CartItemServiceImpl;
import com.netshop.service.implement.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();
	private CartItemService cartItemService = new CartItemServiceImpl();

	/**
	 * ��ȡ��ǰҳ��
	 * 
	 * @param req
	 * @return
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if (param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {
			}
		}
		return pc;
	}

	/**
	 * ��ȡurl��ҳ���еķ�ҳ��������Ҫʹ������Ϊ�����ӵ�Ŀ�꣡
	 * 
	 * @param req
	 * @return
	 */
	/*
	 * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=
	 * 3 /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
	 */
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		/*
		 * ���url�д���pc��������ȡ��������������ǾͲ��ý�ȡ��
		 */
		int index = url.lastIndexOf("&pc=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	/**
	 * �ҵĶ���
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int pc = getPc(req);

		String url = getUrl(req);

		User user = (User) req.getSession().getAttribute("usersession");
		PageBean<Order> pb = orderService.myOrders(user.getU_id(), pc);

		List<Order> orders = pb.getBeanList();
		for (Order ord : orders) {

			double money = 0;
			List<OrderItem> orderItems = ord.getOrderItemList();
			for (OrderItem orderItem : orderItems) {
				money += orderItem.getSubtotal();
			}
		}

		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/order/list.jsp";
	}

	/**
	 * ���ض���
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �ӽ����ȡ��������id��ֵ
		String oid = req.getParameter("oid");
		// ͨ��id������
		Order order = orderService.load(oid);
		// ���ص�orderȥ
		req.setAttribute("order", order);

		String btn = req.getParameter("btn");
		req.setAttribute("btn", btn);
		return "/jsps/order/desc.jsp";
	}

	/**
	 * ���ض���+��ַ
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String load_aid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		// �ӽ����ȡ��������id��ֵ
		String oid = req.getParameter("oid");
		// ͨ��id������
		Order order = orderService.load(oid);
		// ���ص�orderȥ
		req.setAttribute("order", order);

		// ��ȡĬ�ϵ�ַ��id
		User user = (User) req.getSession().getAttribute("usersession");
		UserAddress userAddress = orderService.findByaddress(user.getU_id());
		req.setAttribute("orderr", userAddress);

		// �����ܼ�
		List<OrderItem> orderItems = order.getOrderItemList();
		double money = 0;
		for (OrderItem ord : orderItems) {
			money += ord.getSubtotal();
		}
		req.setAttribute("loadmoney", money);

		String btn = req.getParameter("btn");
		req.setAttribute("btn", btn);
		return "/jsps/order/desc.jsp";
	}

	/**
	 * ȷ���ջ�
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String confirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String oid = req.getParameter("oid");
		/*
		 * У�鶩��״̬
		 */
		int status = orderService.findStatus(oid);
		if (status != 3) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "״̬���ԣ�����ȷ���ջ���");
			return "f:/jsps/msg.jsp";
		}
		orderService.updateStatus(oid, 4);// ����״̬Ϊ���׳ɹ���
		req.setAttribute("code", "success");
		req.setAttribute("msg", "��ϲ�����׳ɹ���");
		return "f:/jsps/msg.jsp";
	}

	/**
	 * ���ɶ���
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public String createOrder(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException, ParseException {
		/*
		 * 1. ��ȡ���й��ﳵ��Ŀ��id����ѯ
		 */
		String cartItemIds = req.getParameter("cartItemIds");
		List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);

		/*
		 * 2. ����Order
		 */
		Order order = new Order();
		order.setOid(CommonUtils.uuid());// ��������

		
		// ֱ�ӻ�ȡʱ��
		Date date = new Date();
		order.setOdate(date);// �µ�ʱ��
		order.setStatus(1);// ����״̬��1��ʾδ����

		// //��ȡ�û���Ϣ
		User owner = (User) req.getSession().getAttribute("usersession");
		// /*
		// * 1.ͨ��session����ȡ���û���Ϣ
		// * 2.ʹ��addrservice�еķ��������owner�����е�ַ
		// * 3.������ַ���ҵ�Ĭ�ϵ�ַ��id
		// */
		//
		// if (owner == null) {
		// return "f:/jsps/msg.jsp";
		// }
		// int uid=owner.getU_id();
		// int aid = 0 ;
		// List<UserAddress> userAddressList = addrService.loadaddress(uid);

		User user = (User) req.getSession().getAttribute("usersession");
		UserAddress userAddress = orderService.findByaddress(user.getU_id());
		int addrid=userAddress.getA_id();
		order.setAddress(userAddress);// �����ջ���ַ
		order.setAddrid(addrid);
		order.setOuid(owner.getU_id());// ���ö���������
		/*
		 * BigDecimal total = new BigDecimal("0"); for(CartItem cartItem :
		 * cartItemList) { total = total.add(new
		 * BigDecimal(cartItem.getSubtotal() + "")); }
		 * order.setTotal(total.doubleValue());
		 */

		/*
		 * 3. ����List<OrderItem> һ��CartItem��Ӧһ��OrderItem
		 */
		double money = 0;
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (CartItem cartItem : cartItemList) {
			OrderItem orderItem = new OrderItem();
			// orderItem.setOrderItemId(CommonUtils.uuid());//��������
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setItem(cartItem.getItems());
			orderItem.setOrder(order);

			orderItemList.add(orderItem);
			money += cartItem.getSubtotal();
			// System.out.println(orderItem.getItem().getItem_price());
		}
		order.setTotal(money);

		req.setAttribute("money", money);
		order.setOrderItemList(orderItemList);
		/*
		 * 4. ����service�������
		 */
		orderService.createOrder(order);

		// ɾ�����ﳵ��Ŀ
		cartItemService.Delete(cartItemIds);
		/*
		 * 5. ���涩����ת����ordersucc.jsp
		 */
		req.setAttribute("order", order);
		return "f:/jsps/order/ordersuc.jsp";
	}

	/**
	 * ȡ������
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String cancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oid = req.getParameter("oid");
		/*
		 * У�鶩��״̬
		 */
		int status = orderService.findStatus(oid);
		if (status != 1) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "״̬���ԣ�����ȡ����");
			return "f:/jsps/msg.jsp";
		}
		orderService.updateStatus(oid, 5);// ����״̬Ϊȡ����
		req.setAttribute("code", "success");
		req.setAttribute("msg", "���Ķ�����ȡ�������������");
		return "f:/jsps/msg.jsp";
	}

	/**
	 * ֧��
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void paymentPre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.sendRedirect("http://www.alipay.com");
	}

}