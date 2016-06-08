package com.netshop.service;

import com.netshop.exception.UserException;
import com.netshop.model.User;

public interface UserService {
	//登录
	public User login(User user);
	
	//注册
	public void regist(User user);
	
	//修改密码
	public void updatePassword(int uid, String newPass, String oldPass) throws UserException;
	
	//校验用户名是否注册
	public boolean ajaxValidateLoginname(String uname);
}
