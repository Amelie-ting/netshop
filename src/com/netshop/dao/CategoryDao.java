package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Category;

public interface CategoryDao {
	//�������з���
	public List<Category> findAll() throws SQLException;
	
	//��ӷ���
	public void add(Category category) throws SQLException;
	
	//ͨ���������ѯ�ӷ���
	public List<Category> findByParent(String pid) throws SQLException;
    
	//��ȡ���и����࣬�������ӷ����
	public List<Category> findParents() throws SQLException;
}
