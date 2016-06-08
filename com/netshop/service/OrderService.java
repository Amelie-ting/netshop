package com.netshop.service;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Order;
import com.netshop.model.UserAddress;
import com.netshop.pager.PageBean;

/**
 * @ClassName: OrderService
 * @Description: ��������㷽���ӿ�
 * @author hdm
 * @date ����ʱ�䣺2016��4��5�� ����11:33:28 @version=1.0
 */
public interface OrderService {
	// �޸Ķ���״̬
	public void updateStatus(String oid, int status);

	// ��ѯ����״̬
	public int findStatus(String oid);

	// ���ض���
	public Order load(String oid);

	// ���ɶ���
	public void createOrder(Order order);

	// �ҵĶ���
	public List<Order> myOrders(int uid);

	// ��״̬��ѯ
	public List<Order> findByStatus(int status);

	// ��ѯ����
	// public List<Order> findAll();

	// ��ʾ�ջ���ַ
	public UserAddress findByaddress(int uid) throws SQLException;

}
