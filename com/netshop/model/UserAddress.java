package com.netshop.model;

/** 
* @ClassName: UserAddress
* @Description:�û��ջ���ַʵ����
* @author  hdm
* @date ����ʱ�䣺2016��4��10�� ����4:39:48
* @version=1.0
*/
public class UserAddress {
	private int a_id;//����
	private int a_uid;
	private String a_uname;//�ջ�������
	private User user;//�û�id
	private String a_state;//�û�����ʡ
	private String a_city;//�û�������
	private String a_county;//�û�������
	private String a_addr;//�û����ھ����ַ
	private String a_tel;//�û����ڵ��ʱ�
	private String a_zip;//�绰
	private String a_isDefault;//�Ƿ���Ĭ�ϵ�ַ
	
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
