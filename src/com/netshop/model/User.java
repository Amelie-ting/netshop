package com.netshop.model;

/**
 * @Description: �û�ģ��ʵ����
 */
public class User {

	private Integer u_id;// �û�ID
	private String u_name;// �û���¼��
	private String u_password;// �û���¼����
	private String u_nickname;// �û��ǳ�

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public String getU_nickname() {
		return u_nickname;
	}

	public void setU_nickname(String u_nickname) {
		this.u_nickname = u_nickname;
	}

	public User() {
	}
}
