package com.netshop.pager;

import java.util.List;

/**
 * @ClassName: PageBean
 * @Description: 分页Bean，它会在各层之间传递！
 * @author hdm
 * @date 创建时间：2016年4月1日 下午4:24:05 @version=1.0
 */
public class PageBean<T> {
	private int pc;// 当前页码
	private int tr;// 总记录数
	private int ps;// 每页记录数
	private String url;// 请求路径和参数，例如：/ItemsServlet?method=findXXX&item_id=1&iitem_name=2
	public List<T> beanList;

	// 计算总页数
	public int getTp() {
		int tp = tr / ps;
		return tr % ps == 0 ? tp : tp + 1;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}
