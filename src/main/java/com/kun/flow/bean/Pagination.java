package com.kun.flow.bean;

/**
 * 分页对象，维护显示页面分页信息
 * 
 */
public class Pagination {

	// 每页大小缺省值
	public static final int PAGE_SIZE_DEFAULT = 10;

	private int pageNumber = 0;

	private int pageSize = PAGE_SIZE_DEFAULT;

	private int totalRows = 0;

	public Pagination() {
	}

	/**
	 * 取每页大小
	 * 
	 * @since
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页大小
	 * 
	 * @since
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 设置每页大小
	 * 
	 * @since
	 * @param pageSize
	 */
	public void setPageSize(String pageSize) {
		if (pageSize != null && pageSize.length() > 0) {
			this.setPageSize(Integer.parseInt(pageSize));
		} else {
			this.setPageSize(PAGE_SIZE_DEFAULT);
		}
	}

	/**
	 * 设置记录的总条数
	 * 
	 * @since
	 * @param totalRows
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * 取记录总条数
	 * 
	 * @since
	 * @return
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * 获取起始页数
	 * 
	 * @author songkun
	 * @date Jul 13, 2010 8:10:53 PM
	 * @return int
	 * @return
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 设置起始页数
	 * 
	 * @author songkun
	 * @date Jul 13, 2010 8:11:00 PM
	 * @return void
	 * @param start
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 获取起始位置
	 * 
	 * @author songkun
	 * @create 2014年5月4日 下午4:49:19
	 * @since
	 * @return
	 */
	public int getStart() {
		if (this.pageNumber < 1 || this.pageSize < 1) {
			return 0;
		}
		return this.pageSize * (this.pageNumber - 1);
	}

	/**
	 * 适配easyUI的分页参数
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午1:45:00
	 * @since
	 * @param pageNumber
	 */
	public void setPage(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 适配easyUI的分页参数
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午1:45:22
	 * @since
	 * @param pageSize
	 */
	public void setRows(int pageSize) {
		this.pageSize = pageSize;
	}
}
