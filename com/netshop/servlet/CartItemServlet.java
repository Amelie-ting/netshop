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
	 * 加载多个CartItem
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadCartItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取cartItemIds参数
		String cartItemIds = req.getParameter("cartItemIds");
		double total = Double.parseDouble(req.getParameter("total"));

		// 通过service得到List<CartItem>
		List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);

		// 保存，然后转发到/cart/showitem.jsp
		req.setAttribute("cartItemList", cartItemList);
		req.setAttribute("total", total);
		req.setAttribute("cartItemIds", cartItemIds);
		return "f:/jsps/cart/showitem.jsp";
	}

	//更改数量
	public String updateQuantity(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cartItemId = req.getParameter("cartItemId");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		CartItem criteria = cartItemService.updateQuantity(cartItemId, quantity);

		// 给客户端返回一个json对象
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"quantity\"").append(":").append(criteria.getQuantity());
		sb.append(",");
		sb.append("\"subtotal\"").append(":").append(criteria.getSubtotal());
		sb.append("}");

		resp.getWriter().print(sb);
		return null;
	}

	/**
	 * 批量删除功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取cartItemIds参数 2. 调用service方法完成工作 3. 返回到list.jsp
		String cartItemIds = req.getParameter("cartItemIds");
		cartItemService.Delete(cartItemIds);
		return myCart(req, resp);
	}

	/**
	 * 添加购物车条目
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 封装表单数据到CartItem(bid, quantity)
		/*Map map = req.getParameterMap();
		CartItem cartitem = CommonUtils.toBean(map, CartItem.class);
		Items item = CommonUtils.toBean(map, Items.class);
		User user = (User) req.getSession().getAttribute("usersession");
		cartitem.setItems(item);
		cartitem.setUser(user);*/
		
		/*
		 * 这里按照项目里提供的方法注入是不行的，还是需要通过getparameter来获得参数
		 * 然后再将参数添加进去
		 * 至于为什么按照上面的方法加不进去，我也不知道。。。。。。。
		 */
		Map map=req.getParameterMap();
		int  id=Integer.parseInt(req.getParameter("bid"));
		int  quantity=Integer.parseInt(req.getParameter("quantity"));
		CartItem cartitem = CommonUtils.toBean(map, CartItem.class);
		Items item = CommonUtils.toBean(map, Items.class);
		User user = (User) req.getSession().getAttribute("usersession");
		
		//检验用户是否登陆
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
		
		// 调用service完成添加
		cartItemService.add(cartitem);
		
		// 查询出当前用户的所有条目，转发到list.jsp显示
		return myCart(req, resp);
	}

	/**
	 * 购物车
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 得到uid
		User user = (User) req.getSession().getAttribute("usersession");
		int uid = user.getU_id();

		// 通过service得到当前用户的所有购物车条目
		List<CartItem> cartItemLIst = cartItemService.myCart(uid);

		// 保存起来，转发到/cart/list.jsp
		req.setAttribute("cartItemList", cartItemLIst);
		return "f:/jsps/cart/list.jsp";
	}
}
