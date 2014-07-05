package com.kun.flow.exception;

/**
 * 数据库访问异常
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月23日 下午7:00:58
 */
public class DataException extends Exception {
	private static final long serialVersionUID = 1L;

	public DataException() {
		super("dao.DataAccessException");
	}

	public DataException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public DataException(String messageKey) {
		super(messageKey);
	}

	public DataException(Throwable cause) {
		super("dao.DataAccessException", cause);
	}

}
