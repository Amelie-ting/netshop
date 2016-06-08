package com.netshop.service.implement;

import java.sql.SQLException;
import java.util.List;

import com.netshop.commons.CommonUtils;
import com.netshop.dao.CartItemDao;
import com.netshop.dao.implement.CartItemDaoImpl;
import com.netshop.model.CartItem;
import com.netshop.service.CartItemService;

/**
 * @ClassName: CartItemServiceImpl
 * @Description: 购物车服务层
 * @author hdm
 * @date 创建时间：2016年4月3日 下午9:52:16 
 * @version=1.0
 */
public class CartItemServiceImpl implements CartItemService {
	private CartItemDao cartItemDao = new CartItemDaoImpl();

	// 加载多个CartItem
	@Override
	public List<CartItem> loadCartItems(String cartItemIds) {
		try {
			return cartItemDao.loadCartItems(cartItemIds);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改购物车条目数量
	 * 
	 * @param cartItemId
	 * @param quantity
	 * @return
	 */
	@Override
	public CartItem updateQuantity(String cartItemId, int quantity) {
		try {
			cartItemDao.updateQuantity(cartItemId, quantity);
			return cartItemDao.findByCartItemId(cartItemId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除功能
	 * 
	 * @param cartItemIds
	 */
	@Override
	public void Delete(String cartItemId) {
		try {
			cartItemDao.batchDelete(cartItemId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加条目
	 * 
	 * @param cartItem
	 */
	@Override
	public void add(CartItem criteria) {
		try {
			/*
			 * 1. 使用uid和iid去数据库中查询这个条目是否存在
			 */
			CartItem _cartItem = cartItemDao.findByUidAndIid(criteria.getUser().getU_id(), criteria.getItems().getItem_id());
			if (_cartItem == null) {// 如果原来没有这个条目，那么添加条目
				criteria.setCartitemid(CommonUtils.uuid());
				cartItemDao.addCartItem(criteria);
			} else {// 如果原来有这个条目，修改数量
				// 使用原有数量和新条目数量之各，来做为新的数量
				int quantity = criteria.getQuantity() + _cartItem.getQuantity();
				// 修改这个老条目的数量
				cartItemDao.updateQuantity(_cartItem.getCartitemid(), quantity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 购物车功能
	 * 
	 * @param uid
	 * @return
	 */
	@Override
	public List<CartItem> myCart(int uid) {
		try {
			return cartItemDao.findByUser(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
