package com.netshop.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.commons.CommonUtils;
import com.netshop.model.CartItem;
import com.netshop.model.Category;
import com.netshop.model.Order;
import com.netshop.model.OrderItem;
import com.netshop.model.User;
import com.netshop.model.UserAddress;
import com.netshop.service.AddressService;
import com.netshop.service.CartItemService;
import com.netshop.service.OrderService;
import com.netshop.service.implement.AddressServiceImpl;
import com.netshop.service.implement.CartItemServiceImpl;
import com.netshop.service.implement.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private  OrderService orderService=new OrderServiceImpl();
    private CartItemService cartItemService=new CartItemServiceImpl();
    private AddressService addrService = new AddressServiceImpl();
    /**
	 * 我的订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myOrders(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User)req.getSession().getAttribute("usersession");
		List<Order> pb = orderService.myOrders(user.getU_id());
//		for(Order ua:pb)
//		{
//			System.out.println(ua.getItem().getItem_name());
//		}
		req.setAttribute("pb", pb);
		return "f:/jsps/order/list.jsp";
	}
	
	/**
	 * 加载订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//从界面获取订单主键id的值
		String oid = req.getParameter("oid");
		//通过id来加载
		Order order = orderService.load(oid);
		//返回到order去
		req.setAttribute("order", order);
		
		String btn = req.getParameter("btn");
		req.setAttribute("btn", btn);
		return "/jsps/order/desc.jsp";
	}
	
	/**
	 * 加载订单+地址
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public String load_aid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		//从界面获取订单主键id的值
		String oid = req.getParameter("oid");
		//通过id来加载
		Order order = orderService.load(oid);
		//返回到order去
		req.setAttribute("order", order);
		
		//获取默认地址的id
		User user = (User)req.getSession().getAttribute("usersession");
		UserAddress userAddress=orderService.findByaddress(user.getU_id());
		req.setAttribute("orderr", userAddress);
		
		//计算总计
		List<OrderItem> orderItems=order.getOrderItemList();
		double money=0;
		for (OrderItem ord:orderItems) {
			 money = ord.getSubtotal();
		}
		req.setAttribute("loadmoney", money);
		
		String btn = req.getParameter("btn");
		req.setAttribute("btn", btn);
		return "/jsps/order/desc.jsp";
	}
	
	/**
	 * 确认收货
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String confirm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String oid = req.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if(status != 3) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "状态不对，不能确认收货！");
			return "f:/jsps/msg.jsp";
		}
		orderService.updateStatus(oid, 4);//设置状态为交易成功！
		req.setAttribute("code", "success");
		req.setAttribute("msg", "恭喜，交易成功！");
		return "f:/jsps/msg.jsp";		
	}
	
	/**
	 * 生成订单
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
		 * 1. 获取所有购物车条目的id，查询
		 */
		String cartItemIds = req.getParameter("cartItemIds");
		List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);
		
		/*
		 * 2. 创建Order
		 */
		Order order = new Order();
		order.setOid(CommonUtils.uuid());//设置主键
		
		//直接获取时间
		Date date=new Date();
		order.setOdate(date);//下单时间
		order.setStatus(1);//设置状态，1表示未付款
		
//		//获取用户信息
		User owner = (User)req.getSession().getAttribute("usersession");
//		/*
//		 * 1.通过session，获取到用户信息
//		 * 2.使用addrservice中的方法，获得owner的所有地址
//		 * 3.遍历地址，找到默认地址的id
//		 */
//		
//		if (owner == null) {
//			return "f:/jsps/msg.jsp";
//		}
//		int uid=owner.getU_id();
//		int aid = 0 ;
//		List<UserAddress> userAddressList = addrService.loadaddress(uid);
//		//通过循环，找到默认地址
//		for(UserAddress ua:userAddressList)
//		{
//			if(ua.getA_isDefault().equals("1"))
//			{
//				aid=ua.getA_id();
//			}
//		}
//		order.setAddrid(aid);//先将找到的aid放入订单中
//		Order neworder=orderService.findByaddress(aid);//通过aid找到默认的地址
		
		User user = (User)req.getSession().getAttribute("usersession");
		UserAddress userAddress=orderService.findByaddress(user.getU_id());
		order.setAddress(userAddress);//设置收货地址
		
		order.setOuid(owner.getU_id());//设置订单所有者
		/*BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : cartItemList) {
			total = total.add(new BigDecimal(cartItem.getSubtotal() + ""));
		}
		order.setTotal(total.doubleValue());*/
		
		/*
		 * 3. 创建List<OrderItem>
		 * 一个CartItem对应一个OrderItem
		 */
		double money = 0;
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(CartItem cartItem : cartItemList) {
			OrderItem orderItem = new OrderItem();
			//orderItem.setOrderItemId(CommonUtils.uuid());//设置主键
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setItem(cartItem.getItems());
			orderItem.setOrder(order);
			orderItemList.add(orderItem);
			money+=cartItem.getSubtotal();
			//System.out.println(orderItem.getItem().getItem_price());
		}		
		req.setAttribute("money",money);
		order.setOrderItemList(orderItemList);
		/*
		 * 4. 调用service完成添加
		 */
		orderService.createOrder(order);
		
		// 删除购物车条目
		cartItemService.Delete(cartItemIds);
		/*
		 * 5. 保存订单，转发到ordersucc.jsp
		 */ 
		req.setAttribute("order", order);
		return "f:/jsps/order/ordersuc.jsp";
	}
	
	
	/**
	 * 取消订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String cancel(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oid = req.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if(status != 1) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "状态不对，不能取消！");
			return "f:/jsps/msg.jsp";
		}
		orderService.updateStatus(oid, 5);//设置状态为取消！
		req.setAttribute("code", "success");
		req.setAttribute("msg", "您的订单已取消，您不后悔吗！");
		return "f:/jsps/msg.jsp";		
	}
	
	/**
	 * 支付
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void paymentPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.sendRedirect("http://www.alipay.com"); 
	}
	
	

}
