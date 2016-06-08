package com.netshop.exception;

/** 
* @ClassName: UserException
* @Description: 用户业务异常
* @author  hdm
* @date 创建时间：2016年4月3日 上午9:55:09
* @version=1.0
*/
public class UserException extends Exception {

	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
