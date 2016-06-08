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
	
	/**
	 * ���ڸ��������룬�ж������ݿ����Ƿ����
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean checkQuery(String barcode) throws SQLException {	
		String sql = "select count(*) from items where barcode=? ";
		String code=barcode;
		Number number = (Number) qr.query(sql, new ScalarHandler(), code);
		//�ҵ�����1���Ҳ�������0
		return number.intValue() > 0;
	}
	
	/**
	 * ���뷽��
	 * �����������벻���ڵ��������ֱ���½�һ����Ʒ
	 * @throws SQLException
	 */
	public void insert(Items items) throws SQLException{
		String sql = "insert into items(barcode��item_come) values(?,?)";
		int num=items.getItem_come();
		String code=items.getBarcode();
		Object[] params = {code,num};
		qr.update(sql, params);
	}
	
	/**
	 * ���·���
	 * ���������������ڵ�����£��������
	 * @throws SQLException 
	 */
	public void update(Items items) throws SQLException{
		String sql ="update items set item_come=? where barcode= ?";
		int num=items.getItem_come();
		String code=items.getBarcode();
		Object[] params = {num,code};
		qr.update(sql, params);
	}
	
	@Test
	public void testUpdateandCheck() throws SQLException{
		Items items=new Items();
		items.setBarcode("12345678910");
		items.setItem_come(10);
		boolean rs=checkQuery(items.getBarcode());
		if (rs) {
			update(items);
			System.out.println("���³ɹ�");
		}
		else {
			insert(items);
			System.out.println("����ɹ�");
		}
	}
	
	
	
}
