package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Category;
import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.pager.PageBean;

/**
 * @ClassName: ItemsDao
 * @Description:��Ʒ��ѯ�ӿ�
 * @author hdm
 * @date ����ʱ�䣺2016��3��31�� ����11:57:43 @version=1.0
 */
public interface ItemsDao {
	// ģ����ѯ������ҳ��
	public List<Items> Fuzzyquery(CriteriaItems cr) throws SQLException;

	// ģ����ѯ����ҳ��
	// public PageBean<Items> findByItemname(String iname, int pc) throws
	// SQLException;

	// ��Iid��ѯ
	public Items findByIid(int Iid) throws SQLException;

	// �����Ʒ
	public void add(Items item) throws SQLException;

	// ������id����
	public List<Items> findByCategory(int cid) throws SQLException;
	
	
	/**
	 * ��ѯָ����������Ʒ�ĸ���
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	public int findBookCountByCategory(String cid) throws SQLException ;

}
