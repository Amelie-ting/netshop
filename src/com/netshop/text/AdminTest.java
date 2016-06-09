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
//	 * ���ڸ��������룬�ж������ݿ����Ƿ����
//	 * @return
//	 * @throws SQLException
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public boolean checkQuery(String barcode) throws SQLException {	
//		String sql = "select count(*) from items where barcode=? ";
//		String code=barcode;
//		Number number = (Number) qr.query(sql, new ScalarHandler(), code);
//		//�ҵ�����1���Ҳ�������0
//		return number.intValue() > 0;
//	}
	/**
	 * ���뷽��
	 * �����������벻���ڵ��������ֱ���½�һ����Ʒ
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
//	 * ���·���
//	 * ���������������ڵ�����£��������
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
//			System.out.println("���³ɹ�");
//		}
//		else {
//			insert(items);
//			System.out.println("����ɹ�");
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
		System.out.println("�ɹ�");
	}
	


}
