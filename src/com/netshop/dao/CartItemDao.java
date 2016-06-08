package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.CartItem;

/** 
* @ClassName: CartitemDao
* @Description: ���ﳵ�־ò�ӿ�
* @author  hdm
* @date ����ʱ�䣺2016��4��3�� ����9:02:14
* @version=1.0
*/
public interface CartItemDao {
	//������id��ѯ
	public CartItem findByCartItemId(String cartItemId) throws SQLException;

	//����ɾ��
	public void batchDelete(String cartItemIds) throws SQLException;
	
	//��ѯĳ���û���ĳ��Ʒ�Ĺ��ﳵ��Ŀ�Ƿ����
	public CartItem findByUidAndIid(int uid, int iid) throws SQLException;

	//�޸�ָ����Ŀ������
	public void updateQuantity(String cartItemId, int quantity) throws SQLException;

	//�����Ŀ
	public void addCartItem(CartItem cartItem) throws SQLException;

	//ͨ���û���ѯ���ﳵ��Ŀ
	public List<CartItem> findByUser(int uid) throws SQLException;

	//���ض��Cartitem
	public List<CartItem> loadCartItems(String cartItemIds) throws SQLException;
}
