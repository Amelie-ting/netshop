package com.netshop.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.netshop.model.Items;

public interface ControlDao {
	/*
	 * 通过上传得到的excel的文件的路径，读取excel文件
	 */
	//入库读取excel方法
	public List<Items> readXlsCome(String excelurl) throws IOException,SQLException;
	
	//出库读取excel方法
	public List<Items> readXlsGo(String excelurl) throws IOException,SQLException;
}
