package com.netshop.model;

import java.sql.Date;

public class Invent {
	private String i_type;
	private int i_innumb;
	private int i_outnumb;
	private int i_sid;
	private Date i_indate;
	private Date i_outdate;
	public String getI_type() {
		return i_type;
	}
	public void setI_type(String i_type) {
		this.i_type = i_type;
	}
	public int getI_innumb() {
		return i_innumb;
	}
	public void setI_innumb(int i_innumb) {
		this.i_innumb = i_innumb;
	}
	public int getI_outnumb() {
		return i_outnumb;
	}
	public void setI_outnumb(int i_outnumb) {
		this.i_outnumb = i_outnumb;
	}
	public int getI_sid() {
		return i_sid;
	}
	public void setI_sid(int i_sid) {
		this.i_sid = i_sid;
	}
	public Date getI_indate() {
		return i_indate;
	}
	public void setI_indate(Date i_indate) {
		this.i_indate = i_indate;
	}
	public Date getI_outdate() {
		return i_outdate;
	}
	public void setI_outdate(Date i_outdate) {
		this.i_outdate = i_outdate;
	}
     
     
}
