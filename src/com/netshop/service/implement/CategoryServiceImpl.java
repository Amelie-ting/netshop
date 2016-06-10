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
	 * 查询所有分类
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
	 * 添加分类
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
	 * 查询指定父分类下的所有子分类
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

	@Override
	public void edit(Category category) {
		try {
			categoryDao.edit(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public Category load(String ca_id) {
		try {
			return categoryDao.load(ca_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public int findChildrenCountByParent(String pid) {
		try {
			return categoryDao.findChildrenCountByParent(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(String cid) {
		try {
			categoryDao.delete(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		
	}

}
