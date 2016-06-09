package com.netshop.dao;

import com.netshop.model.Items;

public interface ItemExcelDao {
	
	/**
	 * 检查item中是否存在条形码，找到就是1，找不到就是0
	 * @param barcode
	 * @return
	 */
	public boolean checkQuery(String barcode);
	
	
	/**
	 * 更新方法，也就是库中存在条形码
	 * @param items
	 */
	public void update(Items items);
	
	/**
	 * 插入方法，也就是库中不存在条形码
	 * @param items
	 */ 
	public void insert(Items items);

}
