package com.netshop.service;

import java.sql.SQLException;
import java.util.List;

import com.netshop.model.UserAddress;

/**
 * @ClassName: AddessService
 * @Description: 用户收货地址服务层方法接口
 * @author hdm
 * @date 创建时间：2016年4月10日 下午4:40:14 @version=1.0
 */
public interface AddressService {
	// 添加收货地址
	public void addAddress(UserAddress addr);

	// 加载收货人地址
	public List<UserAddress> loadaddress(int uid);

	// 删除
	public void delete(int aid);

	//按id查找
	public UserAddress findById(int aid);
	// 修改
	public void update(UserAddress userAddress);
	//设置默认
	public void setDefault(UserAddress userAddress);
	
	public void setNoDefault(UserAddress aaddr);

}
