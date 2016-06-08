package com.netshop.model;

import java.math.BigDecimal;

public class CartItem {
	private Items items;//��Ŀ��Ӧ����Ʒ
	private String cartitemid;// ����
	private int quantity;// ����
	private User  user;// �����û�
	private int orderby;
	
	// ��ӹ��ﳵС�Ʒ���
			public double getSubtotal() {
				/*
				 * ʹ��BigDecimal���������
				 * Ҫ�����ʹ��String���͹�����
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
