package com.netshop.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Items;

public interface ControlDao {
	/*
	 * ͨ���ϴ��õ���excel���ļ���·������ȡexcel�ļ�
	 */
	//����ȡexcel����
	public List<Items> readXlsCome(String excelurl) throws IOException,SQLException;
	
	//�����ȡexcel����
	public List<Items> readXlsGo(String excelurl) throws IOException,SQLException;
}
