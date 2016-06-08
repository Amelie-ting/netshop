package com.netshop.service;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.UserAddress;

/**
 * @ClassName: AddessService
 * @Description: �û��ջ���ַ����㷽���ӿ�
 * @author hdm
 * @date ����ʱ�䣺2016��4��10�� ����4:40:14 @version=1.0
 */
public interface AddressService {
	// ����ջ���ַ
	public void addAddress(UserAddress addr);

	// �����ջ��˵�ַ
	public List<UserAddress> loadaddress(int uid);

	// ɾ��
	public void delete(int aid);

	//��id����
	public UserAddress findById(int aid);
	// �޸�
	public void update(UserAddress userAddress);
	//����Ĭ��
	public void setDefault(UserAddress userAddress);
	
	public void setNoDefault(UserAddress aaddr);

}
