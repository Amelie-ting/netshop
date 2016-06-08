package com.netshop.service;

import com.netshop.exception.UserException;
import com.netshop.model.User;

public interface UserService {
	//��¼
	public User login(User user);
	
	//ע��
	public void regist(User user);
	
	//�޸�����
	public void updatePassword(int uid, String newPass, String oldPass) throws UserException;
	
	//У���û����Ƿ�ע��
	public boolean ajaxValidateLoginname(String uname);
}
