package com.netshop.model;

import java.math.BigDecimal;

public class CartItem {
	private Items items;//条目对应的商品
	private String cartitemid;// 主键
	private int quantity;// 数量
	private User  user;// 所属用户
	private int orderby;
	
	// 添加购物车小计方法
			public double getSubtotal() {
				/*
				 * 使用BigDecimal不会有误差
				 * 要求必须使用String类型构造器
				 */
				BigDecimal item1 = new BigDecimal(items.getItem_price() + "");
				BigDecimal item2 = new BigDecimal(quantity + "");
				BigDecimal item3 = item1.multiply(item2);
				return item3.doubleValue();
			}
			
	
	public Items getItems() {
				return items;
			}


			public void setItems(Items items) {
				this.items = items;
			}


	public String getCartitemid() {
		return cartitemid;
	}
	public void setCartitemid(String cartitemid) {
		this.cartitemid = cartitemid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getOrderby() {
		return orderby;
	}
	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}

}
