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
 * @Description: ���ﳵ�����
 * @author hdm
 * @date ����ʱ�䣺2016��4��3�� ����9:52:16 
 * @version=1.0
 */
public class CartItemServiceImpl implements CartItemService {
	private CartItemDao cartItemDao = new CartItemDaoImpl();

	// ���ض��CartItem
	@Override
	public List<CartItem> loadCartItems(String cartItemIds) {
		try {
			return cartItemDao.loadCartItems(cartItemIds);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �޸Ĺ��ﳵ��Ŀ����
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
	 * ɾ������
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
	 * �����Ŀ
	 * 
	 * @param cartItem
	 */
	@Override
	public void add(CartItem criteria) {
		try {
			/*
			 * 1. ʹ��uid��iidȥ���ݿ��в�ѯ�����Ŀ�Ƿ����
			 */
			CartItem _cartItem = cartItemDao.findByUidAndIid(criteria.getUser().getU_id(), criteria.getItems().getItem_id());
			if (_cartItem == null) {// ���ԭ��û�������Ŀ����ô�����Ŀ
				criteria.setCartitemid(CommonUtils.uuid());
				cartItemDao.addCartItem(criteria);
			} else {// ���ԭ���������Ŀ���޸�����
				// ʹ��ԭ������������Ŀ����֮��������Ϊ�µ�����
				int quantity = criteria.getQuantity() + _cartItem.getQuantity();
				// �޸��������Ŀ������
				cartItemDao.updateQuantity(_cartItem.getCartitemid(), quantity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ���ﳵ����
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
