package com.netshop.model;

import java.util.Date;


/** 
* @Description: 商品模块实体类
*/
public class Items {
	private Integer item_id;// 商品ID，主键
	private int item_wid;// 厂家ID
	private String item_name;// 商品名称
	private double item_price;// 商品销售单价
	private Date item_gdate;// 生产日期
	private int item_caid;// 商品类型id
	private String item_descn;//商品描述
	private Category category;//所属分类
	private String item_pic;//图片
	private double purprice;//进货价
	private String barcode;//条形码
	public String getItem_pic() {
		return item_pic;
	}
	public void setItem_pic(String item_pic) {
		this.item_pic = item_pic;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public int getItem_wid() {
		return item_wid;
	}
	public void setItem_wid(int item_wid) {
		this.item_wid = item_wid;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public double getItem_price() {
		return item_price;
	}
	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}
	public Date getItem_gdate() {
		return item_gdate;
	}
	public void setItem_gdate(Date item_gdate) {
		this.item_gdate = item_gdate;
	}
	public int getItem_caid() {
		return item_caid;
	}
	public void setItem_caid(int item_caid) {
		this.item_caid = item_caid;
	}
	public String getItem_descn() {
		return item_descn;
	}
	public void setItem_descn(String item_descn) {
		this.item_descn = item_descn;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public double getPurprice() {
		return purprice;
	}
	public void setPurprice(double purprice) {
		this.purprice = purprice;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
}
