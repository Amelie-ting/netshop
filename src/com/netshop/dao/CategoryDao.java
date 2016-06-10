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
	
	/**
	 * ���ط�����Ϣ
	 * �ȿ��Լ���һ�����࣬�ֿ��Լ��ض�������
	 * @param ca_id
	 * @return
	 * @throws SQLException 
	 */
	public Category load(String ca_id) throws SQLException;
	
	/**
	 * �����޸ĵķ���
	 * һ���Ͷ������඼�ܴ���
	 * @param category
	 * @return
	 * @throws SQLException 
	 */
	public void edit(Category category) throws SQLException;
	
	
	/**
	 * ��ѯָ�����������ӷ���ĸ���
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public int findChildrenCountByParent(String pid) throws SQLException;
	
	
	/**
	 * ɾ������
	 * @param cid
	 * @throws SQLException 
	 */
	public void delete(String cid) throws SQLException ;
	
	
	

	
}
