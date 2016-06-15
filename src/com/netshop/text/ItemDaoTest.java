package com.netshop.text;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.netshop.dao.ItemsDao;
import com.netshop.dao.implement.ItemsDaoImpl;
import com.netshop.model.ItemBar;
import com.netshop.model.Items;

public class ItemDaoTest {
	
	private ItemsDao itemsDao=new ItemsDaoImpl();
	
//	@Test
//	public void findbar() throws SQLException{
//		List<Items> items=itemsDao.findByBarcode();
//		for(Items example:items){
//			System.out.println(example.getItem_id());
//		}
//	}
	
//	@Test
//	public void findid() throws SQLException{
//		Items items=itemsDao.findByIid(49);
//		System.out.println(items.getBarcode()+" "+items.getItem_stock()+" "+items.getItem_id());
//	}
	
//	@Test
//	public void findbyitembar() throws SQLException 
//	{
//		ItemBar itemBar=itemsDao.findByItemBar(49);
//		System.out.println(itemBar.getItem_id()+" "+itemBar.getItem_stock()+" "+itemBar.getBarcode());
//	}
	
	@Test
	public void findIdByBar() throws SQLException{
		int bar=itemsDao.findIdByBar("123456789");
		System.out.println(bar);
	}

}
