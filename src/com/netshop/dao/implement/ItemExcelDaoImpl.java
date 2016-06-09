package com.netshop.dao.implement;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.netshop.dao.ItemExcelDao;
import com.netshop.jdbc.DAO;
import com.netshop.model.Items;
import com.netshop.model.User;

public class ItemExcelDaoImpl implements ItemExcelDao {
	
	private QueryRunner qr = new DAO();
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean checkQuery(String barcode) throws SQLException {
		String sql = "select count(*) from items where barcode=? ";
		String code=barcode;
		Number number = (Number) qr.query(sql, new ScalarHandler(), code);
		//找到就是1，找不到就是0
		return number.intValue() > 0;
	}

	@Override
	public void update(Items items) throws SQLException {
		String sql ="update items set item_stock=? where barcode= ?";
		String code=items.getBarcode();
		Object[] params = {items.getItem_stock(),code};
		qr.update(sql, params);
	}

	@Override
	public void insert(Items items) throws SQLException {
		String sql = "insert into items(barcode,item_stock) values(?,?)";
		String num=items.getItem_stock();
		String code=items.getBarcode();
		Object[] params = {code,num};
		qr.update(sql, params);

	}

	@Override
	public Items findStock(String barcode) throws SQLException {
		String sql = "select  * from items where barcode=?";
		return qr.query(sql, new BeanHandler<Items>(Items.class), barcode);
	}

}
