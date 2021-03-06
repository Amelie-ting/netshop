package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Category;
import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.pager.PageBean;

/** 
* @ClassName: ItemsDao
* @Description:商品查询接口
* @author  hdm
* @date 创建时间：2016年3月31日 上午11:57:43
* @version=1.0
*/
public interface ItemsDao {
	//模糊查询（不分页）
	public List<Items> Fuzzyquery(CriteriaItems cr) throws SQLException;
    
	//模糊查询（分页）
	public PageBean<Items> findByItemname(String iname, int pc) throws SQLException;
	
	//按Iid查询
	public Items findByIid(int Iid) throws SQLException;
	
	//添加商品
	public void add(Items item) throws SQLException;
	
	///按商品类别查询所有商品
	public List<Category> findAll() throws SQLException;
	//按类别ID查询
	public List<Items> findByCategory(int cid) throws SQLException;
}
