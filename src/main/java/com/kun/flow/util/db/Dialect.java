/**
 * Program  : Dialect.java
 * Author   : songkun
 * Create   : 2014年7月5日 下午4:56:11
 *
 * Copyright 2014 songkun. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of songkun.  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with songkun.
 *
 */

package com.kun.flow.util.db;

import com.kun.flow.bean.Pagination;

/**
 * 
 *
 * @author songkun
 * @version 1.0.0
 * @date 2014年7月5日 下午4:56:11
 */
public abstract class Dialect {

	private static final String MYSQL = "mysql";
	private static final String ORACLE = "oracle";
	private static final String SQLSERVER = "sqlserver";
	private static final String DB2 = "db2";
	private static final String H2 = "h2";

	public abstract String getLimitString(String sql, Pagination pagination);

	public Dialect getInstance(String dataBaseName) {
		switch (dataBaseName) {
			case MYSQL :
				return new MySqlDialect();
			case ORACLE :
				return new OracleDialect();
			case SQLSERVER :
				return new SQLServerDialect();
			case DB2 :
				return new DB2Dialect();
			case H2 :
				return new H2Dialect();
			default :
				return new MySqlDialect();
		}
	}
}
