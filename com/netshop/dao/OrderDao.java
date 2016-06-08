package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Order;
import com.netshop.model.UserAddress;
import com.netshop.pager.PageBean;

/** 
* @ClassName: OrderDao
* @Description: 订单方法接口
* @author  hdm
* @date 创建时间：2016年4月5日 上午10:19:37
* @version=1.0
*/
public interface OrderDao {
	//查询订单状态
	public int findStatus(String oid) throws SQLException;
	
	//修改订单状态
	public void updateStatus(String oid, int status) throws SQLException;
	
	//加载订单
	public Order load(String oid) throws SQLException;
	
	//生成订单
	public void add(Order order) throws SQLException;
	
	//按用户id查询订单
	public List<Order> findByUser(int uid) throws SQLException;

	//查询所有订单
	//public List<Order> findAll() throws SQLException;

	// 按状态查询订单
	public List<Order> findByStatus(int status) throws SQLException;

	//显示收货地址
	public UserAddress findByaddress(int uid) throws SQLException;
}
