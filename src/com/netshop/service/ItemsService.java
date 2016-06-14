package com.netshop.service;

import java.util.List;

import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;

public interface ItemsService {

	// 按商品id加载商品
	public Items loadByIid(int Iid);

	// // 模糊查询
	// public PageBean<Items> findByItemname(String iname, int pc) throws
	// SQLException;

	// 模糊查询（不分页）
	public List<Items> Fuzzyquery(CriteriaItems cr);

	// 添加商品
	public void add(Items item);

	// 按商品类别id加载商品
	public List<Items> findByCategory(int cid);
	
	//查询二级分类下的商品个数
	
	public void edit(Items items);
	
	public int findBookCountByCategory(String cid) ;
	
	public void delete(String id);
}
