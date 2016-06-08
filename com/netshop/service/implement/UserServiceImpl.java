package com.netshop.service.implement;

import java.sql.SQLException;

import com.netshop.dao.UserDao;
import com.netshop.dao.implement.UserDaoImpl;
import com.netshop.exception.UserException;
import com.netshop.model.User;
import com.netshop.service.UserService;


/**
 * @ClassName: UserServiceImpl
 * @Description:�û�ģ��ҵ���
 * @author hdm
 * @date ����ʱ�䣺2016��3��31�� ����9:28:01 @version=1.0
 */
public class UserServiceImpl implements UserService {
	private UserDao userDAO = new UserDaoImpl();

	/**
	 * ��¼����
	 * @param user
	 * @return
	 */
	public User login(User user) {
		try {
			return userDAO.findByLoginnameAndnpass(user.getU_name(), user.getU_password());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ע�Ṧ��
	 * 
	 * @param user
	 * @return
	 */
	public void regist(User user) {

		try {
			userDAO.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �޸�����
	 * @param uid
	 * @param newPass
	 * @param oldPass
	 * @throws UserException
	 */
	public void updatePassword(int uid, String newPass, String oldPass) throws UserException {
		try {
			// У��������
			boolean bool = userDAO.findByUidAndPassword(uid, oldPass);
			if (!bool) {// ������������
				throw new UserException("���������");
			}
			// �޸�����
			userDAO.updatePassword(uid, newPass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �û���ע��У��
	 * @param uname
	 * @return
	 */
	public boolean ajaxValidateLoginname(String uname) {
		try {
			return userDAO.ajaxValidateLoginname(uname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
