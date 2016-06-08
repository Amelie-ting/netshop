package com.netshop.service;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Category;
import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.pager.PageBean;

public interface ItemsService {

	// ����Ʒid������Ʒ
	public Items loadByIid(int Iid);

	// ����Ʒ��� �������и������Ʒ
	public List<Category> loadByType();

	// ģ����ѯ
	public PageBean<Items> findByItemname(String iname, int pc) throws SQLException;

	// ģ����ѯ������ҳ��
	public List<Items> Fuzzyquery(CriteriaItems cr);

	// �����Ʒ
	public void add(Items item);

	// ����Ʒ���id������Ʒ
	public List<Items> findByCategory(int cid);
}
