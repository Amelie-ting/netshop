package com.netshop.model;

/**
 * @ClassName: OrderItem
 * @Description: 订单条目
 * @author hdm
 * @date 创建时间：2016年4月5日 上午11:06:27 @version=1.0
 */
public class OrderItem {
	private String orderItemId;// 主键
	private int quantity;// 数量
	private double subtotal;// 小计
	private Items item;// 所关联的Items
	private Order order;// 所属的订单

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
