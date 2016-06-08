package com.netshop.model;


import java.util.Date;
import java.util.List;


public class Order {
	private String oid;//主键
	private Date odate;//下单时间
	private int status;//订单状态：1未付款, 2已付款但未发货, 3已发货未确认收货, 4确认收货了交易成功, 5已取消(只有未付款才能取消)
	private String uevaluation;//用户评论
	private int ouid;//订单的所有者
	private User user;//所关联的user
	private Items item;
	private OrderItem orderItem;
	private int addrid;//订单收货地址id
	private UserAddress address;//所关联的UserAddress
	private List<OrderItem> orderItemList;
	
	
	public Items getItem() {
		return item;
	}
	public void setItem(Items item) {
		this.item = item;
	}
	public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public int getAddrid() {
		return addrid;
	}
	public void setAddrid(int addrid) {
		this.addrid = addrid;
	}
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUevaluation() {
		return uevaluation;
	}
	public void setUevaluation(String uevaluation) {
		this.uevaluation = uevaluation;
	}
	public int getOuid() {
		return ouid;
	}
	public void setOuid(int ouid) {
		this.ouid = ouid;
	}
	
}
