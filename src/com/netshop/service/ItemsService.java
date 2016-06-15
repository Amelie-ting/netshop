package com.netshop.service;

import java.util.List;

import com.netshop.model.CriteriaItems;
import com.netshop.model.ItemBar;
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
	
	/**
	 * 查所有的新入库
	 * @return
	 */
	public List<Items> findnull();
	
	/**
	 * 根据itemid查itembar类，用于addedit界面
	 * @param item_id
	 * @return
	 */
	public ItemBar findByItemBar(int item_id);
	
	/**
	 * 通过条形码查商品id，用于addedit删除空的item
	 * @param barcode
	 * @return
	 */
	public int findIdByBar(String barcode) ;

}
