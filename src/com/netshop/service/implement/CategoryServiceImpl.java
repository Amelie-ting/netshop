package com.netshop.service.implement;

import java.sql.SQLException;
import java.util.List;

import com.netshop.dao.CategoryDao;
import com.netshop.dao.implement.CategoryDaoImpl;
import com.netshop.model.Category;
import com.netshop.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao categoryDao = new CategoryDaoImpl();

	/**
	 * ��ѯ���з���
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		try {
			return categoryDao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��ӷ���
	 * 
	 * @param category
	 */
	public void add(Category category) {
		try {
			categoryDao.add(category);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Category> findParents() {
		try {
			return categoryDao.findParents();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��ѯָ���������µ������ӷ���
	 * 
	 * @param pid
	 * @return
	 */
	public List<Category> findChildren(String pid) {
		try {
			return categoryDao.findByParent(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
