package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Category;

public interface CategoryDao {
	//查找所有分类
	public List<Category> findAll() throws SQLException;
	
	//添加分类
	public void add(Category category) throws SQLException;
	
	//通过父分类查询子分类
	public List<Category> findByParent(String pid) throws SQLException;
    
	
	//获取所有父分类，但不带子分类的
	public List<Category> findParents() throws SQLException;
	
	/**
	 * 加载分类信息
	 * 既可以加载一级分类，又可以加载二级分类
	 * @param ca_id
	 * @return
	 * @throws SQLException 
	 */
	public Category load(String ca_id) throws SQLException;
	
	/**
	 * 分类修改的方法
	 * 一级和二级分类都能处理
	 * @param category
	 * @return
	 * @throws SQLException 
	 */
	public void edit(Category category) throws SQLException;
	
	
	/**
	 * 查询指定父分类下子分类的个数
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public int findChildrenCountByParent(String pid) throws SQLException;
	
	
	/**
	 * 删除分类
	 * @param cid
	 * @throws SQLException 
	 */
	public void delete(String cid) throws SQLException ;
	
	
	

	
}
