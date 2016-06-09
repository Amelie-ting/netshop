package com.netshop.text;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import org.junit.Test;

import com.netshop.jdbc.DAO;
import com.netshop.model.Items;
import com.netshop.model.User;


public class AdminTest {
	private QueryRunner qr = new DAO();
//	
//	/**
//	 * 用于给出条形码，判断在数据库中是否存在
//	 * @return
//	 * @throws SQLException
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public boolean checkQuery(String barcode) throws SQLException {	
//		String sql = "select count(*) from items where barcode=? ";
//		String code=barcode;
//		Number number = (Number) qr.query(sql, new ScalarHandler(), code);
//		//找到就是1，找不到就是0
//		return number.intValue() > 0;
//	}
	/**
	 * 插入方法
	 * 用于在条形码不存在的情况啊，直接新建一个商品
	 * @throws SQLException
	 */
	public void insert(Items items) throws SQLException{
		String sql = "insert into items(barcode,item_stock) values(?,?)";
		String num=items.getItem_stock();
		String code=items.getBarcode();
		Object[] params = {code,num};
		qr.update(sql, params);
	}
//	/**
//	 * 更新方法
//	 * 用于如果条形码存在的情况下，添加数量
//	 * @throws SQLException 
//	 */
//	public void update(Items items) throws SQLException{
//		String sql ="update items set item_stock=? where barcode= ?";
//		
//		String code=items.getBarcode();
//		Object[] params = {items.getItem_stock(),code};
//		qr.update(sql, params);
//	}
//	@Test
//	public void testUpdateandCheck() throws SQLException{
//		Items items=new Items();
//		items.setBarcode("12345678999");
//		items.setItem_stock("1");
//		boolean rs=checkQuery(items.getBarcode());
//		System.out.println(rs);
//		if (rs) {
//			update(items);
//			System.out.println("更新成功");
//		}
//		else {
//			insert(items);
//			System.out.println("插入成功");
//		}
//	}
	
//	@Test
//	public void add() throws SQLException {
//		String sql = "insert into test(name,age) values(?,?)";
//		Object[] params = { "luca","1"};
//		qr.update(sql, params);
//	}

	@Test
	public void testInsert() throws SQLException{
		Items items=new Items();
		items.setBarcode("123456xxx");
		items.setItem_stock("12");
		insert(items);
		System.out.println(items.getItem_name());
		System.out.println("成功");
	}
	


}
