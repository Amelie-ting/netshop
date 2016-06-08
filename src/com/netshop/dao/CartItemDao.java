package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.CartItem;

/** 
* @ClassName: CartitemDao
* @Description: 购物车持久层接口
* @author  hdm
* @date 创建时间：2016年4月3日 下午9:02:14
* @version=1.0
*/
public interface CartItemDao {
	//按购物id查询
	public CartItem findByCartItemId(String cartItemId) throws SQLException;

	//批量删除
	public void batchDelete(String cartItemIds) throws SQLException;
	
	//查询某个用户的某商品的购物车条目是否存在
	public CartItem findByUidAndIid(int uid, int iid) throws SQLException;

	//修改指定条目的数量
	public void updateQuantity(String cartItemId, int quantity) throws SQLException;

	//添加条目
	public void addCartItem(CartItem cartItem) throws SQLException;

	//通过用户查询购物车条目
	public List<CartItem> findByUser(int uid) throws SQLException;

	//加载多个Cartitem
	public List<CartItem> loadCartItems(String cartItemIds) throws SQLException;
}
