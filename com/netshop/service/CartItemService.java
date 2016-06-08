package com.netshop.service;

import java.util.List;

import com.netshop.model.CartItem;

/** 
* @ClassName: CartItemService
* @Description: 购物车服务层接口
* @author  hdm
* @date 创建时间：2016年4月3日 下午9:52:39
* @version=1.0
*/
public interface CartItemService {

	//加载多个CartItem
	public List<CartItem> loadCartItems(String cartItemIds);
	
	//修改购物车条目数量
	public CartItem updateQuantity(String cartItemId, int quantity);
	
	//删除功能
	public void Delete(String cartItemId);
	
	//添加条目
	public void add(CartItem criteria);
	
	//购物车功能
	public List<CartItem> myCart(int uid);
}
