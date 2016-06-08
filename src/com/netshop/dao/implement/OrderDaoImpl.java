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
 * @Description: 订单持久层
 * @author hdm
 * @date 创建时间：2016年4月5日 上午10:19:56 @version=1.0
 */
public class OrderDaoImpl implements OrderDao {
	private QueryRunner qr = new DAO();

	/**
	 * 查询订单状态
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
	 * 修改订单状态
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
	 * 加载订单
	 * 
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Order load(String oid) throws SQLException {
		String sql = "select * from myorder where oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		loadOrderItem(order);// 为当前订单加载它的所有订单条目
		return order;
	}

	/**
	 * 生成订单
	 * 
	 * @param order
	 * @throws SQLException
	 */
	@Override
	public void add(Order order) throws SQLException {

		// 插入订单
		String sql = "insert  into myorder values(?,?,?,?,?,?,?)";
		Object[] params = { order.getOid(), order.getOuid(), order.getOdate(), order.getUevaluation(),
				order.getStatus(), order.getAddrid(), order.getTotal() };
		qr.update(sql, params);

		// 循环遍历订单的所有条目,让每个条目生成一个Object[] 多个条目就对应Object[][] 执行批处理，完成插入订单条目
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
	 * 查询所有
	 */
	@Override
	public PageBean<Order> findAll(int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		return findByCriteria(exprList, pc);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private PageBean<Order> findByCriteria(List<Expression> exprList, int pc) throws SQLException {
		/*
		 * 1. 得到ps 2. 得到tr 3. 得到beanList 4. 创建PageBean，返回
		 */
		/*
		 * 1. 得到ps
		 */
		int ps = PageConstants.ORDER_PAGE_SIZE;// 每页记录数
		/*
		 * 2. 通过exprList来生成where子句
		 */
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();// SQL中有问号，它是对应问号的值
		for (Expression expr : exprList) {
			/*
			 * 添加一个条件上， 1) 以and开头 2) 条件的名称 3) 条件的运算符，可以是=、!=、>、< ... is null，is
			 * null没有值 4) 如果条件不是is null，再追加问号，然后再向params中添加一与问号对应的值
			 */
			whereSql.append(" and ").append(expr.getName()).append(" ").append(expr.getOperator()).append(" ");
			// where 1=1 and id = ?
			if (!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}

		/*
		 * 3. 总记录数
		 */
		String sql = "select count(*) from myorder " + whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(), params.toArray());
		int tr = number.intValue();// 得到了总记录数
		/*
		 * 4. 得到beanList，即当前页记录
		 */
		sql = "select * from myorder " + whereSql + " order by odate desc limit ?,?";
		params.add((pc - 1) * ps);// 当前页首行记录的下标
		params.add(ps);// 一共查询几行，就是每页记录数

		List<Order> beanList = qr.query(sql, new BeanListHandler<Order>(Order.class), params.toArray());
		// 虽然已经获取所有的订单，但每个订单中并没有订单条目。
		// 遍历每个订单，为其加载它的所有订单条目
		for (Order order : beanList) {
			loadOrderItem(order);
		}

		/*
		 * 5. 创建PageBean，设置参数
		 */
		PageBean<Order> pb = new PageBean<Order>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	// 为指定的order载它的所有OrderItem
	private void loadOrderItem(Order order) throws SQLException {
		/*
		 * 1. 给sql语句select * from t_orderitem where oid=? 2.
		 * 执行，得到List<OrderItem> 3. 设置给Order对象
		 */
		String sql = "select * from orderitem m,items i where m.itemid=i.item_id and oid=?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
		List<OrderItem> orderItemList = toOrderItemList(mapList);

		order.setOrderItemList(orderItemList);
	}

	/**
	 * 把多个Map转换成多个OrderItem
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

	// 把一个Map转换成一个OrderItem
	private Order toOrder(Map<String, Object> map) {
		Order order = CommonUtils.toBean(map, Order.class);
		User user = CommonUtils.toBean(map, User.class);

		Items items = CommonUtils.toBean(map, Items.class);
		order.setUser(user);

		order.setItem(items);
		return order;
	}

	/**
	 * 把多个Map转换成多个OrderItem
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

	// 把一个Map转换成一个OrderItem
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Items item = CommonUtils.toBean(map, Items.class);
		orderItem.setItem(item);
		return orderItem;
	}

	/**
	 * 按用户id查询
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
		// 两者建立关系
		address.setUser(user);
		return address;
	}

	/**
	 * 按用户id查询订单
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
