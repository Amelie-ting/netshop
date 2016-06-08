package com.netshop.pager;

public class Expression {

	private String name; // Ãû³Æ
	private String operator; // ÔËËã·û
	private int value; // Öµ

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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Expression(String name, String operator, int value) {
		super();
		this.name = name;
		this.operator = operator;
		this.value = value;
	}

	public Expression() {
		super();
		// TODO Auto-generated constructor stub
	}

}
