package com.netshop.text;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.netshop.dao.OrderDao;
import com.netshop.dao.implement.OrderDaoImpl;
import com.netshop.dao.implement.OrderDaoImpl;
import com.netshop.model.Items;
import com.netshop.model.Order;
import com.netshop.model.OrderItem;
import com.netshop.model.User;
import com.netshop.model.UserAddress;
import com.netshop.service.OrderService;
import com.netshop.service.implement.OrderServiceImpl;


public class OrderDaoImplText {
  OrderDao orderdao =new OrderDaoImpl();
  
//	@Test
//	public void testFindStatus() throws SQLException {
//		int status=orderdao.findStatus("s11111ss");
//		System.out.println(status);
//	}
//
//	@Test
//	public void testUpdateStatus() throws SQLException {
//		orderdao.updateStatus("s11111ss", 1);
//	}

//	@Test
//	public void testLoad() throws SQLException {
//		Order id=orderdao.load("33A544FBBCD345E9B648BCADFAAF302B");
//		//Order id=orderdao.load("s11111ss");
//		List<OrderItem> orderItems = id.getOrderItemList();
//		//通过循环，找到默认地址
//		for(OrderItem ua:orderItems)
//		{
//			System.out.println(ua.getItem().getItem_price());
//		}
//		System.out.println(id.getAddress().getA_city());
////		System.out.println(id.getItem().getItem_price());
////		System.out.println(id.getOid());
////		System.out.println(id.getStatus());
////		System.out.println(id.getOuid());
//	}

	
//	@Test
//	public void testAdd() throws SQLException, ParseException {
//		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
//		Date date=new Date();
//		Date time= simpleDateFormat.parse("2013/12/21");
//		Order order=new Order();
//		order.setOid("22223");
//		order.setOuid(1);
//		order.setOdate(time);
//		order.setUevaluation(null);
//		order.setStatus(2);
//		@SuppressWarnings("unchecked")
//		List<OrderItem> orderItem=(List<OrderItem>) new OrderItem();
//		Items item=new Items();
//		item.setItem_id(2);
//		item.setItem_name("xxxx");
//		((OrderItem) orderItem).setItem(item);
//		((OrderItem) orderItem).setOrderItemId("2222222");
//		((OrderItem) orderItem).setQuantity(1);
//		((OrderItem) orderItem).setSubtotal(30);
//		orderdao.add(order);
//	}
//
//	@Test
//	public void testFindByUser() throws SQLException {
//		List<Order> or=orderdao.findByUser(11);
//		for (Order ord:or) {
//			System.out.println(ord.getAddress().getA_county());
//		}
//	}
	
	@Test
	public void testFindByUser1() throws SQLException {
		OrderService orderService=new OrderServiceImpl();
		List<Order> or=orderService.myOrders(2);
		for (Order ord:or) {
			System.out.println(ord);
			System.out.println(ord.getItem().getItem_name());
			System.out.println("          ");
		}
	}
	
//	@Test
//	public void testFindAll() {
//		
//	}

//	@Test
//	public void testFindByStatus() throws SQLException {
//		List<Order> or=orderdao.findByStatus(1);
//		for (Order ord:or) {
//			System.out.println(ord.getOid());
//			
//		}
//	}
  
//    @Test
//	public void testLoadOrderItem() throws SQLException {
//    	Order or=orderdao.load("0D9EBEB3EF9D471DB277D78DE010F16D");
//    	System.out.println(or.getAddress().getA_county());
//  }
    
    @Test
   	public void testfindByaddress() throws SQLException {
    	UserAddress userAddress=orderdao.findByaddress(2);
    	System.out.println(userAddress.getA_city());
    }
  
  
  
    
    

}
