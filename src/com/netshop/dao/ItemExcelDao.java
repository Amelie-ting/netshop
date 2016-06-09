package com.netshop.dao;

import java.sql.SQLException;

import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList.Member2.Item;

import com.netshop.model.Items;


public interface ItemExcelDao {
	
	/**
	 * 检查item中是否存在条形码，找到就是1，找不到就是0
	 * @param barcode
	 * @return
	 * @throws SQLException 
	 */
	public boolean checkQuery(String barcode) throws SQLException;
	
	
	/**
	 * 更新方法，也就是库中存在条形码
	 * @param items
	 * @throws SQLException 
	 */
	public void update(Items items) throws SQLException;
	
	
	/**
	 * 插入方法，也就是库中不存在条形码
	 * @param items
	 * @throws SQLException 
	 */ 
	public void insert(Items items) throws SQLException;
	
	/**
	 * 获得库存，根据条形码获得库存数量
	 * @param barcode
	 * @return
	 * @throws SQLException 
	 */
	public Items findStock(String barcode) throws SQLException ;
	
	/**
	 * 用于截取从excel表格中获得的数据
	 * @param items
	 */
	public void sub(Items items);
	
}
