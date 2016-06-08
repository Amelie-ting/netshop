package com.netshop.text;

import java.sql.SQLException;

import org.junit.Test;

import com.netshop.dao.UserDao;
import com.netshop.dao.implement.UserDaoImpl;
import com.netshop.exception.UserException;
import com.netshop.model.User;
import com.netshop.service.UserService;
import com.netshop.service.implement.UserServiceImpl;

public class UserServiceText {
	UserService userService = new UserServiceImpl();

	@Test
	public void testLogin() {
		User user = new User();
		user.setU_name("");
		user.setU_password("");
		if (null != userService.login(user))
			System.out.println("------OK!!-----");
		else
			System.out.println("------Sorry!!-----");
	}

	@Test
	public void testRegister() {
		User user = new User();
		user.setU_name("");
		user.setU_password("");
		userService.regist(user);
		System.out.println("ok");
	}

	@Test
	public void testUpdate() throws UserException {
		userService.updatePassword(38, "123", "222");
	}
}
