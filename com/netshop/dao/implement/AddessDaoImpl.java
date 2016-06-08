package com.netshop.dao.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;


import com.netshop.commons.CommonUtils;
import com.netshop.dao.AddessDao;
import com.netshop.jdbc.DAO;

import com.netshop.model.User;
import com.netshop.model.UserAddress;

/** 
* @ClassName: AddessDaoImpl
* @Description: �û��ջ���ַ�־ò�
* @author  hdm
* @date ����ʱ�䣺2016��4��10�� ����4:39:25
* @version=1.0
*/
public class AddessDaoImpl implements AddessDao {

	private QueryRunner qr = new DAO();
	
	@Override
	public void addAddress(UserAddress addr) throws SQLException {
		String sql = "insert into useraddress(a_uid,a_uname,a_state,a_city,a_county," +
				"a_addr,a_tel,a_zip,a_isDefault) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {addr.getA_uid(),addr.getA_uname(),addr.getA_state(),
				addr.getA_city(),addr.getA_county(),addr.getA_addr(),
				addr.getA_tel(),addr.getA_zip(),addr.getA_isDefault()};
		qr.update(sql, params);

	}
	
	/**
	 * ���ض��addressIds
	 * @param addressIds
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public List<UserAddress> findaddressByUser(int uid) throws SQLException {
		String sql = "SELECT * FROM useraddress a,user u where a.a_uid=u.u_id and u_id=?";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),uid);
		
		
		return  toAddressList(mapList) ;
	}
	
	/*
	 * �Ѷ��Map(List<Map>)ӳ��ɶ��UserAddress(List<UserAddress>)
	 */
	private List<UserAddress> toAddressList(List<Map<String,Object>> mapList) {
		List<UserAddress> userAddressList = new ArrayList<UserAddress>();
		for(Map<String,Object> map : mapList) {
			UserAddress address = toUserAddress(map);
			userAddressList.add(address);
		}
		return userAddressList;
	}

	/*
	 * ��һ��Mapӳ���һ��toUserAddress
	 */
	private UserAddress toUserAddress(Map<String,Object> map) {
		if(map == null || map.size() == 0) return null;
		UserAddress address=CommonUtils.toBean(map, UserAddress.class);
		User user = CommonUtils.toBean(map, User.class);
		address.setUser(user);
		return address;
	}

	/**
	 * ɾ��
	 * @param aid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public void delete(int aid) throws SQLException {
		String sql = "delete from useraddress where a_id=?";
		qr.update(sql, aid);
		
	}
	
	/**
	 * ��id����
	 * @param aid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public UserAddress findById(int aid) throws SQLException {
		String sql = "select * from useraddress where a_id=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(), aid);
		// ��Map�г���id�������������ӳ�䵽UserAddress������
		UserAddress address = CommonUtils.toBean(map, UserAddress.class);
		
		return address;
		
	}

	/**
	 * �޸�
	 * @param aid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public void update(UserAddress userAddr) throws SQLException {
		String sql = "update useraddress set a_uname=?,a_state=?,a_city=?,a_county=?,a_addr=?,a_tel=?,a_zip=?,a_isDefault=? where a_id=?";
		Object[] params = {userAddr.getA_uname(),userAddr.getA_state(),userAddr.getA_city(),
				userAddr.getA_county(),userAddr.getA_addr(),userAddr.getA_tel(),userAddr.getA_zip(),
				userAddr.getA_isDefault(),userAddr.getA_id()};
		qr.update(sql, params);
		
	}

	@Override
	public void setDefault(int id) throws SQLException {
		String sql="update useraddress set a_isDefault='1' where a_id=?";
		qr.update(sql, id);
	}

	@Override
	public void setNoDefault(int userid) throws SQLException {
		String sql="update useraddress set a_isDefault='2' where a_uid=? and a_isDefault='1'";
		qr.update(sql, userid);
		
	}
}
