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
	 * ��һ��Map�е�����ӳ�䵽Category��
	 */
	private Category toCategory(Map<String, Object> map) {

		Category category = CommonUtils.toBean(map, Category.class);
		String pid = (String) map.get("ca_pid");// �����һ�����࣬��ôca_pid��null

		if (pid != null) {// ���������ID��Ϊ�գ�
			/*
			 * ʹ��һ�����������������ca_pid �ٰѸ��������ø�category
			 */
			Category parent = new Category();
			parent.setCa_id(pid);
			;
			category.setParent(parent);
		}

		return category;
	}

	/*
	 * ���԰Ѷ��Map(List<Map>)ӳ��ɶ��Category(List<Category>)
	 */
	private List<Category> toCategoryList(List<Map<String, Object>> mapList) {
		List<Category> categoryList = new ArrayList<Category>();// ����һ���ռ���
		for (Map<String, Object> map : mapList) {// ѭ������ÿ��Map
			Category c = toCategory(map);// ��һ��Mapת����һ��Category
			categoryList.add(c);// ��ӵ�������
		}
		return categoryList;// ���ؼ���
	}

	/**
	 * �������з���
	 * 
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Category> findAll() throws SQLException {
		/*
		 * 1. ��ѯ������һ������
		 */
		String sql = "select * from category where ca_pid is null order by orderBy";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());

		List<Category> parents = toCategoryList(mapList);

		/*
		 * 2. ѭ���������е�һ�����࣬Ϊÿ��һ������������Ķ�������
		 */
		for (Category parent : parents) {
			// ��ѯ����ǰ������������ӷ���
			List<Category> children = findByParent(parent.getCa_id());
			// ���ø�������
			parent.setChildren(children);
		}
		return parents;
	}

	/**
	 * ͨ���������ѯ�ӷ���
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
	 * ��ȡ���и����࣬�������ӷ���ģ�
	 * 
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Category> findParents() throws SQLException {
		/*
		 * 1. ��ѯ������һ������
		 */
		String sql = "select * from category where ca_pid is null order by orderBy";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());

		return toCategoryList(mapList);
	}

	/**
	 * ��ӷ���
	 * 
	 * @param category
	 * @throws SQLException
	 */
	@Override
	public void add(Category category) throws SQLException {
		String sql = "insert into category(ca_id,ca_name,ca_pid) values(?,?,?)";
		/*
		 * ��Ϊһ�����࣬û��parent�������������У� �������������Ҫ�������η��࣬������Ҫ�ж�
		 */
		String pid = null;// һ������
		if (category.getParent() != null) {
			pid = category.getParent().getCa_id();
		}
		Object[] params = { category.getCa_id(), category.getCa_name(), pid };
		qr.update(sql, params);
	}

}
