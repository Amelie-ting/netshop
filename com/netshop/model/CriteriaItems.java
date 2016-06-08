package com.netshop.model;

/**
 * @ClassName: CriteriaItems
 * @Description:商品扩展对象类
 * @author hdm
 * @date 创建时间：2016年3月31日 上午11:13:38 @version=1.0
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
