package com.netshop.model;

import java.math.BigDecimal;
import java.util.List;

import com.netshop.model.Category;
import com.netshop.model.Items;


/** 
* @ClassName: Criteria
* @Description: 扩展对象(包装类)
* @author  hdm
* @date 创建时间：2016年4月3日 下午6:16:12
* @version=1.0
*/
public class Criteria {

	private Items items;
	private Category category;//所属分类
	private String cartitemid;// 主键
	private User user;// 所属用户
	private CartItem cartitem;//购物车id，主键
	
	
	// 添加购物车小计方法
		public double getSubtotal() {
			/*
			 * 使用BigDecimal不会有误差
			 * 要求必须使用String类型构造器
			 */
			BigDecimal item1 = new BigDecimal(items.getItem_price() + "");
			BigDecimal item2 = new BigDecimal(cartitem.getQuantity() + "");
			BigDecimal item3 = item1.multiply(item2);
			return item3.doubleValue();
		}
		
		public String getCartitemid() {
			return cartitemid;
		}
		public Items getItems() {
			return items;
		}
		public void setItems(Items items) {
			this.items = items;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
		
		public void setCartitemid(String cartitemid) {
			this.cartitemid = cartitemid;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		
		public CartItem getCartitem() {
			return cartitem;
		}
		public void setCartitem(CartItem cartitem) {
			this.cartitem = cartitem;
		}

}
