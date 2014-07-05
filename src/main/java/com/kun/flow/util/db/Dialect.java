/**
 * Program  : Dialect.java
 * Author   : songkun
 * Create   : 2014年7月5日 下午4:56:11
 *
 */

package com.kun.flow.util.db;

import com.kun.flow.bean.Pagination;

/**
 * 数据库方言类
 *
 * @author songkun
 * @version 1.0.0
 * @date 2014年7月5日 下午4:56:11
 */
public abstract class Dialect {

	public static final String MYSQL = "mysql";
	public static final String ORACLE = "oracle";
	public static final String SQLSERVER = "sqlserver";
	public static final String DB2 = "db2";
	public static final String H2 = "h2";

	/**
	 * 获取分页sql,具体实现都是从hibernate中拷贝而来,copy from hibernate
	 * 
	 * @author songkun
	 * @create 2014年7月5日 下午5:43:18
	 * @param sql
	 * @param pagination
	 * @return
	 * @return String
	 */
	public abstract String getLimitString(String sql, Pagination pagination);

	/**
	 * 获取具体方言
	 * 
	 * @author songkun
	 * @create 2014年7月5日 下午6:01:34
	 * @param dataBaseName
	 * @return
	 * @return Dialect
	 */
	public static final Dialect getInstance(String dataBaseName) {
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
