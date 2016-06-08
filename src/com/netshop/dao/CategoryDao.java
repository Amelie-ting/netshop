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
}
