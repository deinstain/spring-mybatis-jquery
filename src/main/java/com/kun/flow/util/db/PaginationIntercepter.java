/**
 * PaginationIntercepter.java
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年7月3日 下午11:05:57
 */
package com.kun.flow.util.db;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import com.kun.flow.bean.Pagination;

/**
 * do not use it , coding it
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年7月3日 下午11:05:57
 */
public class PaginationIntercepter implements Interceptor {

	private String databaseName = MYSQL;

	private static final String MYSQL = "mysql";
	private static final String ORACLE = "oracle";
	private static final String SQLSERVER = "sqlserver";
	private static final String DB2 = "db2";

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		Pagination pagination = getPaginationParamter(invocation);
		if (pagination == null) {
			return invocation.proceed();
		}
		BoundSql boundSql = handler.getBoundSql();

		// 获取当前要执行的Sql语句，重写成分页Sql语句
		// 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
		return invocation.proceed();
	}

	private String getPaginationSql(String sql, Pagination pagination) {
		boolean hasOffset = (pagination.getPageNumber() == 1);
		if (MYSQL.equals(databaseName)) {
//			MySqlDialect.getLimitString(sql, hasOffset);
		}
		return sql;
	}
	/**
	 * 获取Mysql数据库的分页查询语句
	 * 
	 * @param page
	 *            分页对象
	 * @param sqlBuffer
	 *            包含原sql语句的StringBuffer对象
	 * @return Mysql数据库分页语句
	 */
	private String getMysqlPageSql(int pageNumber, int pageSize, StringBuilder sb) {
		// 计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		int offset = (pageNumber - 1) * pageSize;
		sb.append(" limit ").append(offset).append(",").append(pageSize);
		return sb.toString();
	}

	/**
	 * 获取Oracle数据库的分页查询语句
	 * 
	 * @param page
	 *            分页对象
	 * @param sqlBuffer
	 *            包含原sql语句的StringBuffer对象
	 * @return Oracle数据库的分页查询语句
	 */
	private String getOraclePageSql(int pageNumber, int pageSize, StringBuilder sb) {
		// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
		int offset = (pageNumber - 1) * pageSize + 1;
		sb.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + pageSize);
		sb.insert(0, "select * from (").append(") where r >= ").append(offset);
		// 上面的Sql语句拼接之后大概是这个样子：
		// select * from (select u.*, rownum r from (select * from t_user) u
		// where rownum < 31) where r >= 16
		return sb.toString();
	}

	/**
	 * 包装目标类(Plugin.wrap(),即,代理)
	 * 
	 * @author songkun
	 * @create 2014年7月5日 下午2:47:10
	 * @since
	 * @param target
	 * @return Object
	 */
	@Override
	public Object plugin(Object target) {
		// 能够走到这里的target为以下几种(可以推断Signature注解该写什么)：
		// (1):org.apache.ibatis.executor.Executor(调用Configuration.newExecutor时,以下同)
		// (2):org.apache.ibatis.executor.parameter.ParameterHandler(newParameterHandler)
		// (3):org.apache.ibatis.executor.resultset.ResultSetHandler(newResultSetHandler)
		// (4):org.apache.ibatis.executor.statement.StatementHandler(newStatementHandler)
		// 当目标类是StatementHandler类型时，才可能包装目标类，否者直接返回目标本身,减少目标被代理的次数
		if (target instanceof StatementHandler) {
			StatementHandler tmp = (StatementHandler) target;
			// 如果是select 语句,则，包装
			// update/insert/delete……不包装
			// 也可以判断sqlCommandType:target-->delegate-->mappedStatement-->sqlCommandType,delegate是私有属性
			if (tmp.getBoundSql() != null && this.startWithSelect(tmp.getBoundSql().getSql())) {
				return Plugin.wrap(target, this);
			}
		}
		return target;
	}

	/**
	 * 获取Mapper方法的Pagination参数
	 * 
	 * @author songkun
	 * @create 2014年7月5日 下午2:59:48
	 * @since
	 * @param invocation
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Pagination getPaginationParamter(Invocation invocation) {
		if (invocation == null || invocation.getTarget() == null) {
			return null;
		}
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		if (handler == null || handler.getParameterHandler() == null) {
			return null;
		}
		Object parameters = handler.getParameterHandler().getParameterObject();
		if (parameters == null) {
			return null;
		}
		// 如果mapper方法只有pagination参数，则直接返回
		if (parameters instanceof Pagination) {
			return (Pagination) parameters;
		}
		// 如果是多个参数，则判断有没有pagination参数
		if (parameters instanceof Map) {
			Map tmp = (Map) parameters;
			for (Iterator iterator = tmp.values().iterator(); iterator.hasNext();) {
				Object next = iterator.next();
				if (next instanceof Pagination) {
					return (Pagination) next;
				}
			}
		}
		return null;
	}

	/**
	 * 判断是否是select语句
	 * 
	 * @author songkun
	 * @create 2014年7月5日 下午2:56:18
	 * @since
	 * @param sql
	 * @return
	 */
	private boolean startWithSelect(String sql) {
		if (sql == null) {
			return false;
		}
		String tmp = sql.trim();
		if (tmp.length() < 6) {
			return false;
		}
		if ("select".equalsIgnoreCase(tmp.substring(0, 6))) {
			return true;
		}
		return false;
	}

	@Override
	public void setProperties(Properties properties) {
		try {
			String prop = properties.getProperty("databaseName").trim().toLowerCase();
			if (prop.equals(MYSQL) || prop.equals(ORACLE)) {
				databaseName = prop;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
