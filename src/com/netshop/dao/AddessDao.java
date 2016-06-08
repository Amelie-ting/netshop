package com.netshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.UserAddress;

/**
 * @ClassName: AddessDao
 * @Description: 用户收货地址接口
 * @author hdm
 * @date 创建时间：2016年4月10日 下午4:38:45 @version=1.0
 */
public interface AddessDao {
	// 添加收货人地址信息
	public void addAddress(UserAddress address) throws SQLException;

	// 加载收货人地址
	public List<UserAddress> findaddressByUser(int uid) throws SQLException;

	// 删除
	public void delete(int aid) throws SQLException;

	// 按id查找
	public UserAddress findById(int aid) throws SQLException;

	// 修改
	public void update(UserAddress userAddress) throws SQLException;

	// 设置默认
	public void setDefault(int id) throws SQLException;

	// 取消默认
	public void setNoDefault(int userid) throws SQLException;

}
