package com.netshop.service;

import java.util.List;

import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;

public interface ItemsService {

	// ����Ʒid������Ʒ
	public Items loadByIid(int Iid);

	// // ģ����ѯ
	// public PageBean<Items> findByItemname(String iname, int pc) throws
	// SQLException;

	// ģ����ѯ������ҳ��
	public List<Items> Fuzzyquery(CriteriaItems cr);

	// �����Ʒ
	public void add(Items item);

	// ����Ʒ���id������Ʒ
	public List<Items> findByCategory(int cid);
	
	//��ѯ���������µ���Ʒ����
	
	public void edit(Items items);
	
	public int findBookCountByCategory(String cid) ;
	
	public void delete(String id);
}
