package com.netshop.dao;

import com.netshop.model.Items;

public interface ItemExcelDao {
	
	/**
	 * ���item���Ƿ���������룬�ҵ�����1���Ҳ�������0
	 * @param barcode
	 * @return
	 */
	public boolean checkQuery(String barcode);
	
	
	/**
	 * ���·�����Ҳ���ǿ��д���������
	 * @param items
	 */
	public void update(Items items);
	
	/**
	 * ���뷽����Ҳ���ǿ��в�����������
	 * @param items
	 */ 
	public void insert(Items items);

}
