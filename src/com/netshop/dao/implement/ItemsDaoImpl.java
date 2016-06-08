package com.netshop.dao.implement;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.netshop.commons.CommonUtils;
import com.netshop.dao.ItemsDao;
import com.netshop.jdbc.DAO;
import com.netshop.model.Category;
import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.pager.Expression;
import com.netshop.pager.PageBean;
import com.netshop.pager.PageConstants;

/**
 * @ClassName: ItemsDaoImpl
 * @Description: ��Ʒģ��־ò�
 * @author hdm
 * @date ����ʱ�䣺2016��3��31�� ����11:57:43 @version=1.0
 */
public class ItemsDaoImpl implements ItemsDao {
	private QueryRunner qr = new DAO();

	// /**
	// * ����Ʒ��ģ����ѯ����ҳ��
	// *
	// * @param iname
	// * @param pc
	// * @return
	// * @throws SQLException
	// */
	// @Override
	// public PageBean<Items> findByItemname(String iname, int pc) throws
	// SQLException {
	// List<Expression> exprList = new ArrayList<Expression>();
	// exprList.add(new Expression("item_name", "like", "%" + iname + "%"));
	// return findByCriteria(exprList, pc);
	// }
	/**
	 * ����Ʒ��ģ����ѯ
	 * 
	 * @param cr
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Items> Fuzzyquery(CriteriaItems cr) throws SQLException {
		String sql = "select * from items where item_name like ?";
		return qr.query(sql, new BeanListHandler<>(Items.class), cr.getItem_name());

	}

	/**
	 * ��Iid��ѯ
	 * 
	 * @param Iid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Items findByIid(int Iid) throws SQLException {
		String sql = "SELECT * FROM items i,category c where c.ca_id=i.item_caid  AND item_id=?";
		
		Map<String, Object> map = qr.query(sql, new MapHandler(), Iid);
		// ��Map�г���ca_id�������������ӳ�䵽Items������
		Items item = CommonUtils.toBean(map, Items.class);
		// ��Map��ca_id����ӳ�䵽Category�У������Categoryֻ��ca_id
		Category category = CommonUtils.toBean(map, Category.class);
		// ���߽�����ϵ
		item.setCategory(category);

		return item;
	}

	/**
	 * ͨ�õĲ�ѯ����
	 * 
	 * @param exprList
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	private PageBean<Items> findByCriteria(List<Expression> exprList, int pc) throws SQLException {
		int ps = PageConstants.Item_PAGE_SIZE;// ÿҳ��¼��

		// ͨ��exprList������where�Ӿ�
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();// SQL�����ʺţ����Ƕ�Ӧ�ʺŵ�ֵ
		for (Expression expr : exprList) {
			/*
			 * ���һ�������ϣ� 1) ��and��ͷ 2) ���������� 3) �������������������=��!=��>��< ... is null��is
			 * nullû��ֵ 4) �����������is null����׷���ʺţ�Ȼ������params�����һ���ʺŶ�Ӧ��ֵ
			 */
			whereSql.append(" or ").append(expr.getName()).append(" ").append(expr.getOperator()).append(" ");
			// where 1=1 and item_id = ?
			if (!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}
		// �ܼ�¼��
		String sql = "select count(*) from items,category" + whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(), params.toArray());
		int tr = number.intValue();// �õ����ܼ�¼��

		// �õ�beanList������ǰҳ��¼
		sql = "select * from items i,category c," + whereSql + " order by item_id limit ?,?";
		params.add((pc - 1) * ps);// ��ǰҳ���м�¼���±�
		params.add(ps);// һ����ѯ���У�����ÿҳ��¼��

		List<Items> beanList = qr.query(sql, new BeanListHandler<Items>(Items.class), params.toArray());

		// ����PageBean�����ò���
		PageBean<Items> pb = new PageBean<Items>();

		// ����PageBeanû��url�����������Servlet���
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	/**
	 * �������ѯ
	 * 
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Items> findByCategory(int cid) throws SQLException {
		String sql = "select * from category c,items i where c.ca_id=i.item_caid  and ca_id=?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), cid);
		return toCategoryList(mapList);
	}

	private List<Items> toCategoryList(List<Map<String, Object>> mapList) {
		List<Items> categoryList = new ArrayList<Items>();// ����һ���ռ���
		for (Map<String, Object> map : mapList) {// ѭ������ÿ��Map
			Items c = toCategory(map);
			categoryList.add(c);// ��ӵ�������
		}
		return categoryList;// ���ؼ���
	}

	private Items toCategory(Map<String, Object> map) {

		Items item = CommonUtils.toBean(map, Items.class);
		Category category = CommonUtils.toBean(map, Category.class);
		item.setCategory(category);

		return item;
	}

	/**
	 * �����Ʒ
	 * 
	 * @param item
	 * @throws SQLException
	 */
	@Override
	public void add(Items item) throws SQLException {
		String sql = "insert into items(item_name,item_caid,item_gdate,item_descn,"
				+ "item_price,purprice,item_wid,item_pic) values(?,?,?,?,?,?,?,?)";
		Object[] params = { item.getItem_name(), item.getItem_caid(), item.getItem_gdate(), item.getItem_descn(),
				item.getItem_price(), item.getPurprice(), item.getItem_wid(), item.getItem_pic() };
		qr.update(sql, params);
	}
}
