package com.netshop.model;

import java.math.BigDecimal;
import java.util.List;

import com.netshop.model.Category;
import com.netshop.model.Items;


/** 
* @ClassName: Criteria
* @Description: ��չ����(��װ��)
* @author  hdm
* @date ����ʱ�䣺2016��4��3�� ����6:16:12
* @version=1.0
*/
public class Criteria {

	private Items items;
	private Category category;//��������
	private String cartitemid;// ����
	private User user;// �����û�
	private CartItem cartitem;//���ﳵid������
	
	
	// ��ӹ��ﳵС�Ʒ���
		public double getSubtotal() {
			/*
			 * ʹ��BigDecimal���������
			 * Ҫ�����ʹ��String���͹�����
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
