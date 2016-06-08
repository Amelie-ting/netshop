package com.netshop.dao.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.netshop.commons.CommonUtils;
import com.netshop.dao.OrderDao;
import com.netshop.jdbc.DAO;
import com.netshop.model.Items;
import com.netshop.model.Order;
import com.netshop.model.OrderItem;
import com.netshop.model.User;
import com.netshop.model.UserAddress;

/**
 * @ClassName: OrderDaoImpl
 * @Description: �����־ò�
 * @author hdm
 * @date ����ʱ�䣺2016��4��5�� ����10:19:56 @version=1.0
 */
public class OrderDaoImpl implements OrderDao {
	private QueryRunner qr = new DAO();

	/**
	 * ��ѯ����״̬
	 * 
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int findStatus(String oid) throws SQLException {
		String sql = "select status from myorder where oid=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(), oid);
		return number.intValue();
	}

	/**
	 * �޸Ķ���״̬
	 * 
	 * @param oid
	 * @param status
	 * @throws SQLException
	 */
	@Override
	public void updateStatus(String oid, int status) throws SQLException {
		String sql = "update myorder set status=? where oid=?";
		qr.update(sql, status, oid);
	}

	/**
	 * ���ض���
	 * 
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Order load(String oid) throws SQLException {
		String sql = "select * from myorder where oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		loadOrderItem(order);// Ϊ��ǰ���������������ж�����Ŀ
		return order;
	}

	/**
	 * ���ɶ���
	 * 
	 * @param order
	 * @throws SQLException
	 */
	@Override
	public void add(Order order) throws SQLException {

		// ���붩��
		String sql = "insert  into myorder values(?,?,?,?,?,?)";
		Object[] params = { order.getOid(),  order.getOuid(),order.getOdate(),
				order.getUevaluation(), order.getStatus(), order.getAddrid() };
		qr.update(sql, params);

		// ѭ������������������Ŀ,��ÿ����Ŀ����һ��Object[] �����Ŀ�Ͷ�ӦObject[][] ִ����������ɲ��붩����Ŀ
		sql = "insert into orderitem values(?,?,?,?,?,?,?,?)";
		int len = order.getOrderItemList().size();
		Object[][] objs = new Object[len][];
		for (int i = 0; i < len; i++) {
			OrderItem item = order.getOrderItemList().get(i);
			objs[i] = new Object[] { item.getOrderItemId(), item.getQuantity(), item.getSubtotal(),
					item.getItem().getItem_id(), item.getItem().getItem_name(), item.getItem().getItem_price(),
					item.getItem().getItem_pic(), order.getOid() };
		}
		qr.batch(sql, objs);
	}

	/**
	 * ���û�id��ѯ����
	 * 
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Order> findByUser(int uid) throws SQLException {
		String sql = "SELECT * FROM myorder m,user u,items i, orderitem o WHERE m.ouid=u.u_id AND m.oid=o.oid AND o.itemid=i.item_id  AND u_id=?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), uid);
		return toOrderList(mapList);

	}

	// /**
	// * ��ѯ����
	// */
	// @Override
	// public List<Order> findAll() throws SQLException {
	// String sql = "SELECT * FROM myorder m,user u where m.ouid=u.u_id";
	// List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
	// return toOrderList(mapList) ;
	// }

	/**
	 * ��ѯ״̬
	 * 
	 * @param status
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Order> findByStatus(int status) throws SQLException {
		String sql = "SELECT * FROM myorder  where  status=?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), status);
		return toOrderList(mapList);
	}

	// Ϊָ����order����������OrderItem
	private void loadOrderItem(Order order) throws SQLException {
		/*
		 * 1. ��sql���select * from t_orderitem where oid=? 2.
		 * ִ�У��õ�List<OrderItem> 3. ���ø�Order����
		 */
		String sql = "select * from orderitem m,items i where m.itemid=i.item_id and oid=?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
		List<OrderItem> orderItemList = toOrderItemList(mapList);

		order.setOrderItemList(orderItemList);
	}

	/**
	 * �Ѷ��Mapת���ɶ��OrderItem
	 * 
	 * @param mapList
	 * @return
	 */
	private List<Order> toOrderList(List<Map<String, Object>> mapList) {
		List<Order> orderList = new ArrayList<Order>();
		for (Map<String, Object> map : mapList) {
			Order order = toOrder(map);
			orderList.add(order);
		}
		return orderList;
	}

	// ��һ��Mapת����һ��OrderItem
	private Order toOrder(Map<String, Object> map) {
		Order order = CommonUtils.toBean(map, Order.class);
		User user = CommonUtils.toBean(map, User.class);
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Items items = CommonUtils.toBean(map, Items.class);
		order.setUser(user);
		order.setOrderItem(orderItem);
		order.setItem(items);
		return order;
	}

	/**
	 * �Ѷ��Mapת���ɶ��OrderItem
	 * 
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (Map<String, Object> map : mapList) {
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}

	// ��һ��Mapת����һ��OrderItem
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Items item = CommonUtils.toBean(map, Items.class);
		orderItem.setItem(item);
		return orderItem;
	}

	/**
	 * ��Iid��ѯ
	 * 
	 * @param bid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public UserAddress findByaddress(int uid) throws SQLException {
		String sql = "SELECT ua.* FROM user u,useraddress ua where u.u_id=ua.a_uid  AND a_isDefault=1 and u.u_id=?";
		Map<String, Object> map = qr.query(sql, new MapHandler(), uid);
        User user = CommonUtils.toBean(map, User.class);
		UserAddress address = CommonUtils.toBean(map, UserAddress.class);
		// ���߽�����ϵ
		address.setUser(user);;
		;

		return address;
	}

}
