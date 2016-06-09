package com.netshop.dao;

import java.sql.SQLException;

import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList.Member2.Item;

import com.netshop.model.Items;


public interface ItemExcelDao {
	
	/**
	 * ���item���Ƿ���������룬�ҵ�����1���Ҳ�������0
	 * @param barcode
	 * @return
	 * @throws SQLException 
	 */
	public boolean checkQuery(String barcode) throws SQLException;
	
	
	/**
	 * ���·�����Ҳ���ǿ��д���������
	 * @param items
	 * @throws SQLException 
	 */
	public void update(Items items) throws SQLException;
	
	
	/**
	 * ���뷽����Ҳ���ǿ��в�����������
	 * @param items
	 * @throws SQLException 
	 */ 
	public void insert(Items items) throws SQLException;
	
	/**
	 * ��ÿ�棬�����������ÿ������
	 * @param barcode
	 * @return
	 * @throws SQLException 
	 */
	public Items findStock(String barcode) throws SQLException ;
	
	/**
	 * ���ڽ�ȡ��excel����л�õ�����
	 * @param items
	 */
	public void sub(Items items);
	
}
