package com.netshop.text;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.netshop.dao.ItemsDao;
import com.netshop.dao.implement.ItemsDaoImpl;
import com.netshop.model.Items;

public class ItemDaoTest {
	
	private ItemsDao itemsDao=new ItemsDaoImpl();
	
	@Test
	public void findbar() throws SQLException{
		List<Items> items=itemsDao.findByBarcode();
		for(Items example:items){
			System.out.println(example.getItem_id());
		}
	}

}
