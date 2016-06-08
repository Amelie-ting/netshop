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
 * @Description: ���ﳵ�־ò�
 * @author hdm
 * @date ����ʱ�䣺2016��4��3�� ����9:02:14 @version=1.0
 */
public class CartItemDaoImpl implements CartItemDao {
	private QueryRunner qr = new DAO();

	// ��������where�Ӿ�
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
	 * ���ض��cartItemIds
	 * 
	 * @param cartItemIds
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<CartItem> loadCartItems(String cartItemIds) throws SQLException {

		// ��cartItemIdsת��������
		Object[] cartItemIdArray = cartItemIds.split(",");

		// ����wehre�Ӿ�
		String whereSql = toWhereSql(cartItemIdArray.length);

		// ����sql���
		String sql = "select * from cartitem c, items i where c.itemid=i.item_id and " + whereSql;

		// ִ��sql������List<CartItem>
		return toCartItemList(qr.query(sql, new MapListHandler(), cartItemIdArray));
	}

	/**
	 * ��id��ѯ
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
	 * ����ɾ��
	 * 
	 * @param cartItemIds
	 * @throws SQLException
	 */
	@Override
	public void batchDelete(String cartItemIds) throws SQLException {
		/*
		 * ��Ҫ�Ȱ�cartItemIdsת�������� 1. ��cartItemIdsת����һ��where�Ӿ� 2. ��delete from
		 * ������һ��Ȼ��ִ��
		 */
		Object[] cartItemIdArray = cartItemIds.split(",");
		String whereSql = toWhereSql(cartItemIdArray.length);
		String sql = "delete from cartitem where " + whereSql;
		qr.update(sql, cartItemIdArray);// ����cartItemIdArray������Object���͵����飡
	}

	/**
	 * ��ѯĳ���û���ĳ����Ʒ�Ĺ��ﳵ��Ŀ�Ƿ����
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
	 * �޸�ָ����Ŀ������
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
	 * �����Ŀ
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
	 * ��һ��Mapӳ���һ��Cartitem
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
	 * �Ѷ��Map(List<Map>)ӳ��ɶ��CartItem(List<CartItem>)
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
	 * ͨ���û���ѯ���ﳵ��Ŀ
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
