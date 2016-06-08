package com.netshop.service;

import java.util.List;

import com.netshop.model.CartItem;

/** 
* @ClassName: CartItemService
* @Description: ���ﳵ�����ӿ�
* @author  hdm
* @date ����ʱ�䣺2016��4��3�� ����9:52:39
* @version=1.0
*/
public interface CartItemService {

	//���ض��CartItem
	public List<CartItem> loadCartItems(String cartItemIds);
	
	//�޸Ĺ��ﳵ��Ŀ����
	public CartItem updateQuantity(String cartItemId, int quantity);
	
	//ɾ������
	public void Delete(String cartItemId);
	
	//�����Ŀ
	public void add(CartItem criteria);
	
	//���ﳵ����
	public List<CartItem> myCart(int uid);
}
