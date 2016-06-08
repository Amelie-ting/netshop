package com.netshop.text;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.netshop.dao.AddessDao;
import com.netshop.dao.implement.AddessDaoImpl;
import com.netshop.model.UserAddress;
import com.netshop.service.AddressService;
import com.netshop.service.implement.AddressServiceImpl;

public class AddressServiceImplText {

//	@Test
//	public void testSetDefault() {
//		  AddressService dao=new AddressServiceImpl();
//		  UserAddress useraddr=new UserAddress();
//		  useraddr.setA_id(13);
//		  dao.setDefault(useraddr);
//		 
//	}

	@Test
	public void testupdate() throws SQLException {
		  AddressService dao=new AddressServiceImpl();
		  AddessDao addr=new AddessDaoImpl();
		  UserAddress useraddr=addr.findById(29);
		  useraddr.setA_uname("ÕÅÈý");
			useraddr.setA_addr("sssssss");
			useraddr.setA_city("dd");
			useraddr.setA_county("dd");
			useraddr.setA_isDefault("1");
			useraddr.setA_state("dd");
			useraddr.setA_zip("ddd");
			useraddr.setA_tel("ddd");
			//useraddr.setA_id(29);
		  dao.update(useraddr);
		  System.out.println("ok");
		 
	}
}
