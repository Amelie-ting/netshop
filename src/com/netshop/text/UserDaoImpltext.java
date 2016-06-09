package com.netshop.text;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import com.netshop.dao.UserDao;
import com.netshop.dao.implement.UserDaoImpl;
import com.netshop.model.User;

public class UserDaoImpltext {
	private UserDao userDAO = new UserDaoImpl();


//	@Test
//
//	public void findByLoginnameAndnpass() throws SQLException {
//		User u=new User();
//		u=userDAO.findByLoginnameAndnpass("xiaoming", "111111");
//		System.out.println(u.getU_name());
//	}
//
//	public void testSave() throws SQLException {
//		User cust = new User();
//		cust.setU_name("");
//		cust.setU_password("");
//		cust.setU_nickname("");
//		userDAO.add(cust);
//	}
//
//	@Test
//	public void updatePassword() throws SQLException {
//
//		userDAO.updatePassword(38, "222");
//	}
//	@Test
//	public void findByUidAndPassword() throws SQLException {
//		boolean bool = userDAO.findByUidAndPassword(38, "212");
//		if (!bool) {
//
//			System.out.println("no");
//		} else {
//			System.out.println("yes");
//		}
//	}
//	@Test
//	public void findByUidAndPassword1() throws SQLException {
//		boolean bool = userDAO.findByUidAndPassword(38, "1");
//		if (!bool) {
//
//			System.out.println("no");
//		} else {
//			System.out.println("yes");
//		}
//	}
	
	public void add(){}

}
