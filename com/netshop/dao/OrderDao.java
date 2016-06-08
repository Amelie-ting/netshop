package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Order;
import com.netshop.model.UserAddress;
import com.netshop.pager.PageBean;

/** 
* @ClassName: OrderDao
* @Description: ���������ӿ�
* @author  hdm
* @date ����ʱ�䣺2016��4��5�� ����10:19:37
* @version=1.0
*/
public interface OrderDao {
	//��ѯ����״̬
	public int findStatus(String oid) throws SQLException;
	
	//�޸Ķ���״̬
	public void updateStatus(String oid, int status) throws SQLException;
	
	//���ض���
	public Order load(String oid) throws SQLException;
	
	//���ɶ���
	public void add(Order order) throws SQLException;
	
	//���û�id��ѯ����
	public List<Order> findByUser(int uid) throws SQLException;

	//��ѯ���ж���
	//public List<Order> findAll() throws SQLException;

	// ��״̬��ѯ����
	public List<Order> findByStatus(int status) throws SQLException;

	//��ʾ�ջ���ַ
	public UserAddress findByaddress(int uid) throws SQLException;
}
