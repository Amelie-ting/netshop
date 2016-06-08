package com.netshop.pager;

public class Expression {
	private String name;  //Ãû³Æ
	private String operator; //ÔËËã·û
	private String value; //Öµ
	private int id;
	
	
	/*
	 * 
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Expression [name=" + name + ", operator=" + operator
				+ ", value=" + value + ", id=" + id + "]";
	}

	public Expression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expression(String name, String operator, String value) {
		super();
		this.name = name;
		this.operator = operator;
		this.value = value;
	}

	public Expression(String operator, String value, int id) {
		super();
		this.operator = operator;
		this.value = value;
		this.id = id;
	}
}
