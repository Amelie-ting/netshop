package com.netshop.service.implement;

import java.sql.SQLException;
import java.util.List;

import com.netshop.dao.OrderDao;
import com.netshop.dao.implement.OrderDaoImpl;
import com.netshop.jdbc.JDBCTools;
import com.netshop.model.Order;
import com.netshop.model.UserAddress;
import com.netshop.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao = new OrderDaoImpl();

	/**
	 * �޸Ķ���״̬
	 * 
	 * @param oid
	 * @param status
	 */
	@Override
	public void updateStatus(String oid, int status) {
		try {
			orderDao.updateStatus(oid, status);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��ѯ����״̬
	 * 
	 * @param oid
	 * @return
	 */
	@Override
	public int findStatus(String oid) {
		try {
			return orderDao.findStatus(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ���ض���
	 * 
	 * @param oid
	 * @return
	 */
	@Override
	public Order load(String oid) {
		try {
			JDBCTools.beginTransaction();
			Order order = orderDao.load(oid);
			JDBCTools.commitTransaction();
			return order;
		} catch (SQLException e) {
			try {
				JDBCTools.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}

	/**
	 * ���ɶ���
	 * 
	 * @param order
	 */
	@Override
	public void createOrder(Order order) {
		try {
			JDBCTools.beginTransaction();
			orderDao.add(order);
			JDBCTools.commitTransaction();
		} catch (SQLException e) {
			try {
				JDBCTools.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}

	}

	/**
	 * �ҵĶ���
	 * 
	 * @param uid
	 * @param pc
	 * @return
	 */
	@Override
	public List<Order> myOrders(int uid) {
		try {
			JDBCTools.beginTransaction();
			List<Order> pb = orderDao.findByUser(uid);
			JDBCTools.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				JDBCTools.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��״̬��ѯ
	 * 
	 * @param status
	 * @param pc
	 * @return
	 */
	@Override
	public List<Order> findByStatus(int status) {
		try {
			JDBCTools.beginTransaction();
			List<Order> pb = orderDao.findByStatus(status);
			JDBCTools.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				JDBCTools.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserAddress findByaddress(int uid) throws SQLException {
		try {
			return orderDao.findByaddress(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��ѯ����
	 * 
	 * @param pc
	 * @return
	 */
//	@Override
//	public List<Order> findAll() {
//		try {
//			JDBCTools.beginTransaction();
//			List<Order> = orderDao.findAll();
//			JDBCTools.commitTransaction();
//			
//		} catch (SQLException e) {
//			try {
//				JDBCTools.rollbackTransaction();
//			} catch (SQLException e1) {
//			}
//			throw new RuntimeException(e);
//		}
//	}
}
