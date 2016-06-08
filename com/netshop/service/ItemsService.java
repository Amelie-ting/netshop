package com.netshop.service;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Category;
import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.pager.PageBean;

public interface ItemsService {

	// 按商品id加载商品
	public Items loadByIid(int Iid);

	// 按商品类别 加载所有该类别商品
	public List<Category> loadByType();

	// 模糊查询
	public PageBean<Items> findByItemname(String iname, int pc) throws SQLException;

	// 模糊查询（不分页）
	public List<Items> Fuzzyquery(CriteriaItems cr);

	// 添加商品
	public void add(Items item);

	// 按商品类别id加载商品
	public List<Items> findByCategory(int cid);
}
