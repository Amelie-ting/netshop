package com.netshop.model;

/**
 * @ClassName: OrderItem
 * @Description: ������Ŀ
 * @author hdm
 * @date ����ʱ�䣺2016��4��5�� ����11:06:27 @version=1.0
 */
public class OrderItem {
	private String orderItemId;// ����
	private int quantity;// ����
	private double subtotal;// С��
	private Items item;// ��������Items
	private Order order;// �����Ķ���

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
