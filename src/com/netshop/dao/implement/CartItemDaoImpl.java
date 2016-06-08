package com.netshop.dao.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.netshop.commons.CommonUtils;
import com.netshop.dao.CartItemDao;
import com.netshop.jdbc.DAO;
import com.netshop.model.CartItem;
import com.netshop.model.Items;
import com.netshop.model.User;

/**
 * @ClassName: CartitemDao
 * @Description: 购物车持久层
 * @author hdm
 * @date 创建时间：2016年4月3日 下午9:02:14 @version=1.0
 */
public class CartItemDaoImpl implements CartItemDao {
	private QueryRunner qr = new DAO();

	// 用来生成where子句
	private String toWhereSql(int len) {
		StringBuilder sb = new StringBuilder("cartitemid in(");
		for (int i = 0; i < len; i++) {
			sb.append("?");
			if (i < len - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * 加载多个cartItemIds
	 * 
	 * @param cartItemIds
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<CartItem> loadCartItems(String cartItemIds) throws SQLException {

		// 把cartItemIds转换成数组
		Object[] cartItemIdArray = cartItemIds.split(",");

		// 生成wehre子句
		String whereSql = toWhereSql(cartItemIdArray.length);

		// 生成sql语句
		String sql = "select * from cartitem c, items i where c.itemid=i.item_id and " + whereSql;

		// 执行sql，返回List<CartItem>
		return toCartItemList(qr.query(sql, new MapListHandler(), cartItemIdArray));
	}

	/**
	 * 按id查询
	 * 
	 * @param cartItemId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public CartItem findByCartItemId(String cartItemId) throws SQLException {
		String sql = "select * from cartitem c, items i where c.itemid=i.item_id and c.cartitemid=?";
		Map<String, Object> map = qr.query(sql, new MapHandler(), cartItemId);
		return toCartItem(map);
	}

	/**
	 * 批量删除
	 * 
	 * @param cartItemIds
	 * @throws SQLException
	 */
	@Override
	public void batchDelete(String cartItemIds) throws SQLException {
		/*
		 * 需要先把cartItemIds转换成数组 1. 把cartItemIds转换成一个where子句 2. 与delete from
		 * 连接在一起，然后执行
		 */
		Object[] cartItemIdArray = cartItemIds.split(",");
		String whereSql = toWhereSql(cartItemIdArray.length);
		String sql = "delete from cartitem where " + whereSql;
		qr.update(sql, cartItemIdArray);// 其中cartItemIdArray必须是Object类型的数组！
	}

	/**
	 * 查询某个用户的某个商品的购物车条目是否存在
	 * 
	 * @throws SQLException
	 */
	@Override
	public CartItem findByUidAndIid(int uid, int iid) throws SQLException {
		String sql = "SELECT * FROM cartitem WHERE uid=? AND itemid=?";
		Map<String, Object> map = qr.query(sql, new MapHandler(), uid, iid);
		return toCartItem(map);
	}

	/**
	 * 修改指定条目的数量
	 * 
	 * @param cartItemId
	 * @param quantity
	 * @throws SQLException
	 */
	@Override
	public void updateQuantity(String cartItemId, int quantity) throws SQLException {
		String sql = "update cartitem set quantity=? where cartitemid=?";
		qr.update(sql, quantity, cartItemId);
	}

	/**
	 * 添加条目
	 * 
	 * @param criteria
	 * @throws SQLException
	 */
	@Override
	public void addCartItem(CartItem criteria) throws SQLException {
		String sql = "insert into cartitem(cartitemid, quantity, itemid, uid) values(?,?,?,?)";
		Object[] params = { criteria.getCartitemid(), criteria.getQuantity(), criteria.getItems().getItem_id(),
				criteria.getUser().getU_id() };
		qr.update(sql, params);
	}

	/*
	 * 把一个Map映射成一个Cartitem
	 */
	private CartItem toCartItem(Map<String, Object> map) {
		if (map == null || map.size() == 0)
			return null;

		CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
		Items item = CommonUtils.toBean(map, Items.class);
		User user = CommonUtils.toBean(map, User.class);
		cartItem.setUser(user);
		cartItem.setItems(item);
		return cartItem;
	}

	/*
	 * 把多个Map(List<Map>)映射成多个CartItem(List<CartItem>)
	 */
	private List<CartItem> toCartItemList(List<Map<String, Object>> mapList) {
		List<CartItem> cartItemList = new ArrayList<CartItem>();
		for (Map<String, Object> map : mapList) {
			CartItem cartItem = toCartItem(map);
			cartItemList.add(cartItem);
		}
		return cartItemList;
	}

	/**
	 * 通过用户查询购物车条目
	 * 
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<CartItem> findByUser(int uid) throws SQLException {
		String sql = "select * from cartitem c, items i where c.itemid=i.item_id and uid=? order by c.orderby";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), uid);
		return toCartItemList(mapList);
	}
}
