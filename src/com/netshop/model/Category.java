package com.netshop.model;

import java.util.List;

/**
 * @ClassName: Category
 * @Description: ����ģ���ʵ����
 * @author hdm
 * @date ����ʱ�䣺2016��4��3�� ����10:22:57 @version=1.0
 */
public class Category {
	private String ca_id;
	private String ca_name;
	private Category parent;// ������
	private List<Category> children;// �ӷ���
	
	public String getCa_id() {
		return ca_id;
	}
	public void setCa_id(String ca_id) {
		this.ca_id = ca_id;
	}
	public String getCa_name() {
		return ca_name;
	}
	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public List<Category> getChildren() {
		return children;
	}
	public void setChildren(List<Category> children) {
		this.children = children;
	}
	
}
