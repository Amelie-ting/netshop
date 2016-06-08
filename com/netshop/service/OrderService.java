package com.netshop.service;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Order;
import com.netshop.model.UserAddress;
import com.netshop.pager.PageBean;

/**
 * @ClassName: OrderService
 * @Description: 订单服务层方法接口
 * @author hdm
 * @date 创建时间：2016年4月5日 上午11:33:28 @version=1.0
 */
public interface OrderService {
	// 修改订单状态
	public void updateStatus(String oid, int status);

	// 查询订单状态
	public int findStatus(String oid);

	// 加载订单
	public Order load(String oid);

	// 生成订单
	public void createOrder(Order order);

	// 我的订单
	public List<Order> myOrders(int uid);

	// 按状态查询
	public List<Order> findByStatus(int status);

	// 查询所有
	// public List<Order> findAll();

	// 显示收货地址
	public UserAddress findByaddress(int uid) throws SQLException;

}
