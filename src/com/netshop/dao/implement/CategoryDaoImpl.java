package com.netshop.dao.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.netshop.commons.CommonUtils;
import com.netshop.dao.CategoryDao;
import com.netshop.jdbc.DAO;
import com.netshop.model.Category;

public class CategoryDaoImpl implements CategoryDao {
	private QueryRunner qr = new DAO();

	/*
	 * 把一个Map中的数据映射到Category中
	 */
	private Category toCategory(Map<String, Object> map) {

		Category category = CommonUtils.toBean(map, Category.class);
		String pid = (String) map.get("ca_pid");// 如果是一级分类，那么ca_pid是null

		if (pid != null) {// 如果父分类ID不为空，
			/*
			 * 使用一个父分类对象来拦截ca_pid 再把父分类设置给category
			 */
			Category parent = new Category();
			parent.setCa_id(pid);
			;
			category.setParent(parent);
		}

		return category;
	}

	/*
	 * 可以把多个Map(List<Map>)映射成多个Category(List<Category>)
	 */
	private List<Category> toCategoryList(List<Map<String, Object>> mapList) {
		List<Category> categoryList = new ArrayList<Category>();// 创建一个空集合
		for (Map<String, Object> map : mapList) {// 循环遍历每个Map
			Category c = toCategory(map);// 把一个Map转换成一个Category
			categoryList.add(c);// 添加到集合中
		}
		return categoryList;// 返回集合
	}

	/**
	 * 返回所有分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Category> findAll() throws SQLException {
		/*
		 * 1. 查询出所有一级分类
		 */
		String sql = "select * from category where ca_pid is null order by orderBy";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());

		List<Category> parents = toCategoryList(mapList);

		/*
		 * 2. 循环遍历所有的一级分类，为每个一级分类加载它的二级分类
		 */
		for (Category parent : parents) {
			// 查询出当前父分类的所有子分类
			List<Category> children = findByParent(parent.getCa_id());
			// 设置给父分类
			parent.setChildren(children);
		}
		return parents;
	}

	/**
	 * 通过父分类查询子分类
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Category> findByParent(String pid) throws SQLException {
		String sql = "select * from category where ca_pid=? order by orderBy";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), pid);
		return toCategoryList(mapList);
	}

	/**
	 * 获取所有父分类，但不带子分类的！
	 * 
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Category> findParents() throws SQLException {
		/*
		 * 1. 查询出所有一级分类
		 */
		String sql = "select * from category where ca_pid is null order by orderBy";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());

		return toCategoryList(mapList);
	}

	/**
	 * 添加分类
	 * 
	 * @param category
	 * @throws SQLException
	 */
	@Override
	public void add(Category category) throws SQLException {
		String sql = "insert into category(ca_id,ca_name,ca_pid) values(?,?,?)";
		/*
		 * 因为一级分类，没有parent，而二级分类有！ 我们这个方法，要兼容两次分类，所以需要判断
		 */
		String pid = null;// 一级分类
		if (category.getParent() != null) {
			pid = category.getParent().getCa_id();
		}
		Object[] params = { category.getCa_id(), category.getCa_name(), pid };
		qr.update(sql, params);
	}

}
