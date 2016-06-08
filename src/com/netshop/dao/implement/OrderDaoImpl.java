package com.netshop.dao.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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
import com.netshop.pager.Expression;
import com.netshop.pager.PageBean;
import com.netshop.pager.PageConstants;

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
		String sql = "insert  into myorder values(?,?,?,?,?,?,?)";
		Object[] params = { order.getOid(), order.getOuid(), order.getOdate(), order.getUevaluation(),
				order.getStatus(), order.getAddrid(), order.getTotal() };
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
	 * ��ѯ����
	 */
	@Override
	public PageBean<Order> findAll(int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		return findByCriteria(exprList, pc);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private PageBean<Order> findByCriteria(List<Expression> exprList, int pc) throws SQLException {
		/*
		 * 1. �õ�ps 2. �õ�tr 3. �õ�beanList 4. ����PageBean������
		 */
		/*
		 * 1. �õ�ps
		 */
		int ps = PageConstants.ORDER_PAGE_SIZE;// ÿҳ��¼��
		/*
		 * 2. ͨ��exprList������where�Ӿ�
		 */
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();// SQL�����ʺţ����Ƕ�Ӧ�ʺŵ�ֵ
		for (Expression expr : exprList) {
			/*
			 * ���һ�������ϣ� 1) ��and��ͷ 2) ���������� 3) �������������������=��!=��>��< ... is null��is
			 * nullû��ֵ 4) �����������is null����׷���ʺţ�Ȼ������params�����һ���ʺŶ�Ӧ��ֵ
			 */
			whereSql.append(" and ").append(expr.getName()).append(" ").append(expr.getOperator()).append(" ");
			// where 1=1 and id = ?
			if (!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}

		/*
		 * 3. �ܼ�¼��
		 */
		String sql = "select count(*) from myorder " + whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(), params.toArray());
		int tr = number.intValue();// �õ����ܼ�¼��
		/*
		 * 4. �õ�beanList������ǰҳ��¼
		 */
		sql = "select * from myorder " + whereSql + " order by odate desc limit ?,?";
		params.add((pc - 1) * ps);// ��ǰҳ���м�¼���±�
		params.add(ps);// һ����ѯ���У�����ÿҳ��¼��

		List<Order> beanList = qr.query(sql, new BeanListHandler<Order>(Order.class), params.toArray());
		// ��Ȼ�Ѿ���ȡ���еĶ�������ÿ�������в�û�ж�����Ŀ��
		// ����ÿ��������Ϊ������������ж�����Ŀ
		for (Order order : beanList) {
			loadOrderItem(order);
		}

		/*
		 * 5. ����PageBean�����ò���
		 */
		PageBean<Order> pb = new PageBean<Order>();
		/*
		 * ����PageBeanû��url�����������Servlet���
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
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

		Items items = CommonUtils.toBean(map, Items.class);
		order.setUser(user);

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
	 * ���û�id��ѯ
	 * 
	 * @param uid
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
		address.setUser(user);
		return address;
	}

	/**
	 * ���û�id��ѯ����
	 * 
	 * @param uid
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	@Override
	public PageBean<Order> findByUser(int uid, int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("ouid", "=", uid));
		return findByCriteria(exprList, pc);
	}

}
