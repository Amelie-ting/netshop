package com.netshop.model;


import java.util.Date;
import java.util.List;


public class Order {
	private String oid;//����
	private Date odate;//�µ�ʱ��
	private int status;//����״̬��1δ����, 2�Ѹ��δ����, 3�ѷ���δȷ���ջ�, 4ȷ���ջ��˽��׳ɹ�, 5��ȡ��(ֻ��δ�������ȡ��)
	private String uevaluation;//�û�����
	private int ouid;//������������
	private User user;//��������user
	private Items item;
	private OrderItem orderItem;
	private int addrid;//�����ջ���ַid
	private UserAddress address;//��������UserAddress
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
