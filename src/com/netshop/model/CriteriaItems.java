package com.netshop.model;

/**
 * @ClassName: CriteriaItems
 * @Description:��Ʒ��չ������
 * @author hdm
 * @date ����ʱ�䣺2016��3��31�� ����11:13:38 @version=1.0
 */
public class CriteriaItems {
	private String item_name;

	public CriteriaItems(String item_name) {
		super();
		this.item_name = item_name;
	}

	public String getItem_name() {
		if (item_name == null)
			item_name = "%%";
		else
			item_name = "%" + item_name + "%";
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
}
