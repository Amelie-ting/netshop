package com.netshop.text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.netshop.dao.ControlDao;
import com.netshop.dao.implement.ControlDaoImpl;
import com.netshop.model.Items;

public class ExcelTest {
	
	ControlDao controlDao=new ControlDaoImpl();
	@Test
	public void testRead() throws IOException, SQLException
	{
		
		List<Items> items=controlDao.readXlsCome("C:/Users/lucah/Desktop/excel.xls");
		System.out.println("work");
		for (Items newitem:items) {
			String start1=newitem.getItem_stock();
			String sub1=start1.substring(0,start1.length()-2);
			
			String start2=newitem.getBarcode();
			String sub2=start2.substring(0,start2.length()-2);
			
			System.out.println(sub1+" "+sub2);
		}
	}

}
