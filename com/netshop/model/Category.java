package com.netshop.model;

/**
 * @ClassName: Category
 * @Description: ����ģ���ʵ����
 * @author hdm
 * @date ����ʱ�䣺2016��4��3�� ����10:22:57 @version=1.0
 */
public class Category {
	private int ca_id;
	private String ca_name;
	private String ca_itemid;
	public int getCa_id() {
		return ca_id;
	}
	public void setCa_id(int ca_id) {
		this.ca_id = ca_id;
	}
	public String getCa_name() {
		return ca_name;
	}
	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}
	public String getCa_itemid() {
		return ca_itemid;
	}
	public void setCa_itemid(String ca_itemid) {
		this.ca_itemid = ca_itemid;
	}

	
}
