package com.netshop.service.implement;

import java.sql.SQLException;

import com.netshop.dao.AdminLoginDao;
import com.netshop.dao.implement.AdminLoginDaoImpl;
import com.netshop.model.Admin;
import com.netshop.service.AdminService;

public class AdminServiceImpl implements AdminService {
	
    AdminLoginDao adminLoginDao=new AdminLoginDaoImpl();
	@Override
	public Admin login(Admin admin) {
		try {
			//return adminDao.find(admin.getAdminname(), admin.getAdminpwd());
			return adminLoginDao.find(admin.getA_name(), admin.getA_password());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
