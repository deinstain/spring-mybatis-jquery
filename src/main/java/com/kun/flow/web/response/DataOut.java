/**
 * Program  : DataOut.java
 * Author   : songkun
 * Create   : 2014年6月28日 下午1:59:34
 *
 */

package com.kun.flow.web.response;

import java.util.List;

import com.kun.flow.bean.Pagination;

/**
 * 数据输出类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年6月28日 下午1:59:34
 */
public class DataOut implements Out {

	private Pagination pagination;
	private List<Object> rows;

	public DataOut(List<Object> rows, Pagination pagination) {
		this.rows = rows;
		this.pagination = pagination;
	}

	@Override
	public List<Object> getRows() {
		return rows;
	}

	@Override
	public String getMessage() {
		return null;
	}

	@Override
	public boolean getSuccess() {
		return true;
	}

	@Override
	public int getTotal() {
		if (this.pagination == null) {
			return 0;
		}
		return this.pagination.getTotalRows();
	}

	// extends
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

}
