package com.netshop.text;

import java.sql.SQLException;

import org.junit.Test;

import com.netshop.dao.AddessDao;
import com.netshop.dao.implement.AddessDaoImpl;
import com.netshop.model.User;
import com.netshop.model.UserAddress;
import com.netshop.service.AddressService;
import com.netshop.service.implement.AddressServiceImpl;

public class AddessDaoText {
	AddessDao addr=new AddessDaoImpl();
//	@Test
//	public void testFindById() throws SQLException {
//		UserAddress useraddr=addr.findById(1);
//		System.out.println(useraddr.getA_uname());
//	}
	@Test
	public void testupdate() throws SQLException {
		AddressService dao=new AddressServiceImpl();
		
		UserAddress useraddr=new UserAddress();
		useraddr.setA_uname("ÕÅÈý");
		useraddr.setA_addr("ddd");
		useraddr.setA_city("dd");
		useraddr.setA_county("dd");
		useraddr.setA_isDefault("1");
		useraddr.setA_state("dd");
		useraddr.setA_zip("ddd");
		useraddr.setA_tel("ddd");
		useraddr.setA_id(29);
		dao.update(useraddr);
		System.out.println(useraddr.getA_isDefault());
	}
	
//	@Test
//	public void testsetDefault() throws SQLException {
//		
//		UserAddress useraddr=new UserAddress();
//		addr.setDefault(1);
//		System.out.println(useraddr.getA_id());
//	}
	 
	  
	  
}
