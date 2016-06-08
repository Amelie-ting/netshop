package com.netshop.model;

/** 
* @ClassName: UserAddress
* @Description:用户收货地址实体类
* @author  hdm
* @date 创建时间：2016年4月10日 下午4:39:48
* @version=1.0
*/
public class UserAddress {
	private int a_id;//主键
	private int a_uid;
	private String a_uname;//收货人姓名
	private User user;//用户id
	private String a_state;//用户所在省
	private String a_city;//用户所在市
	private String a_county;//用户所在区
	private String a_addr;//用户所在具体地址
	private String a_tel;//用户所在地邮编
	private String a_zip;//电话
	private String a_isDefault;//是否是默认地址
	
	public String getA_uname() {
		return a_uname;
	}
	public void setA_uname(String a_uname) {
		this.a_uname = a_uname;
	}
	public int getA_uid() {
		return a_uid;
	}
	public void setA_uid(int a_uid) {
		this.a_uid = a_uid;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getA_county() {
		return a_county;
	}
	public void setA_county(String a_county) {
		this.a_county = a_county;
	}
	public String getA_state() {
		return a_state;
	}
	public void setA_state(String a_state) {
		this.a_state = a_state;
	}
	public String getA_city() {
		return a_city;
	}
	public void setA_city(String a_city) {
		this.a_city = a_city;
	}
	public String getA_addr() {
		return a_addr;
	}
	public void setA_addr(String a_addr) {
		this.a_addr = a_addr;
	}
	public String getA_tel() {
		return a_tel;
	}
	public void setA_tel(String a_tel) {
		this.a_tel = a_tel;
	}
	public String getA_zip() {
		return a_zip;
	}
	public void setA_zip(String a_zip) {
		this.a_zip = a_zip;
	}
	public String getA_isDefault() {
		return a_isDefault;
	}
	public void setA_isDefault(String a_isDefault) {
		this.a_isDefault = a_isDefault;
	}
}
