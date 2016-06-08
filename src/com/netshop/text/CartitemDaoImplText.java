package com.netshop.text;

import java.sql.SQLException;

import org.junit.Test;

import com.netshop.dao.CartItemDao;
import com.netshop.dao.implement.CartItemDaoImpl;
import com.netshop.model.CartItem;
import com.netshop.model.Items;
import com.netshop.model.User;
import com.netshop.service.CartItemService;
import com.netshop.service.implement.CartItemServiceImpl;

public class CartitemDaoImplText {
	CartItemDao dao=new CartItemDaoImpl();

//	@Test
//	public void testLoadCartItems() throws SQLException {
//		
//	}
//
//	@Test
//	public void testFindByCartItemId() throws SQLException {
//		Criteria cr = new Criteria();
//		cr = dao.findByCartItemId("3");
//		System.out.println(cr.getCartitemid());
//		System.out.println(cr.getItems().getItem_name());
//	}
//
//	@Test
//	public void testBatchDelete() throws SQLException {
//		dao.batchDelete("3");
//	}
//
//	@Test
//	public void testFindByUidAndIid() throws SQLException {
//		Criteria cr = new Criteria();
//		cr=dao.findByUidAndIid(21, 2);
//		System.out.println(cr.getCartitemid());
//		System.out.println(cr.getCartitem().getOrderby());
//		System.out.println(cr.getCartitem().getUid());
//	}
//
//	@Test
//	public void testUpdateQuantity() throws SQLException {
//		dao.updateQuantity("3", 5);
//	}

//	@Test
//	public void testAddCartItem() throws SQLException {
//		CartItem cartItem=new CartItem();
//		Items items=new Items();
//		items.setItem_id(2);
//		cartItem.setQuantity(2);
//		User user=new User();
//		user.setU_id(2);
//		user.setU_name("zhangsan");
//		cartItem.setItems(items);;
//		cartItem.setUser(user);
//		cartItem.setCartitemid("33333");
//		dao.addCartItem(cartItem);
//		System.out.println("ok");
//	}
//
//	@Test
//	public void testFindByUser() throws SQLException {
//		
//	}
	@Test
	public void testadd() throws SQLException{
		    CartItemService ItemService=new CartItemServiceImpl();
		   
			CartItem cartItem=new CartItem();
			Items items=new Items();
			items.setItem_id(17);
			cartItem.setQuantity(5);
			User user=new User();
			user.setU_id(2);
			cartItem.setItems(items);
			cartItem.setUser(user);
			//cartItem.setCartitemid("sssssqqqqqq");
			ItemService.add(cartItem);
	}

}
