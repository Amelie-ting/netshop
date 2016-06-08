package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.UserAddress;

/**
 * @ClassName: AddessDao
 * @Description: �û��ջ���ַ�ӿ�
 * @author hdm
 * @date ����ʱ�䣺2016��4��10�� ����4:38:45 @version=1.0
 */
public interface AddessDao {
	// ����ջ��˵�ַ��Ϣ
	public void addAddress(UserAddress address) throws SQLException;

	// �����ջ��˵�ַ
	public List<UserAddress> findaddressByUser(int uid) throws SQLException;

	// ɾ��
	public void delete(int aid) throws SQLException;

	// ��id����
	public UserAddress findById(int aid) throws SQLException;

	// �޸�
	public void update(UserAddress userAddress) throws SQLException;

	// ����Ĭ��
	public void setDefault(int id) throws SQLException;

	// ȡ��Ĭ��
	public void setNoDefault(int userid) throws SQLException;

}
