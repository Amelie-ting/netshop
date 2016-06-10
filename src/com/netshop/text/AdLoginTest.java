package com.netshop.text;

import java.sql.SQLException;

import org.junit.Test;

import com.netshop.dao.AdLoginDao;
import com.netshop.dao.implement.AdLoginDaoImpl;
import com.netshop.model.Admin;

public class AdLoginTest {
	AdLoginDao adLoginDao=new AdLoginDaoImpl();
	
	@Test
	public void test() throws SQLException
	{
		Admin admin=adLoginDao.find("han", "123");
		System.out.println(admin.getA_name());
	}
	

}
