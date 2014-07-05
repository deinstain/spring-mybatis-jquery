/**
 * Program  : Out.java
 * Author   : songkun
 * Create   : 2014年5月3日 下午3:56:30
 *
 */

package com.kun.flow.web.response;

import java.util.List;

/**
 * 输出接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年5月3日 下午3:56:30
 */
public interface Out {
	/**
	 * 获取数据集
	 * 
	 * @author songkun
	 * @create 2014年5月3日 下午3:58:45
	 * @since
	 * @return
	 */
	public List<Object> getRows();

	/**
	 * 获取提示信息
	 * 
	 * @author songkun
	 * @create 2014年5月3日 下午3:58:54
	 * @since
	 * @return
	 */
	public String getMessage();

	/**
	 * 是否成功
	 * 
	 * @author songkun
	 * @create 2014年5月3日 下午3:59:05
	 * @since
	 * @return
	 */
	public boolean getSuccess();

	/**
	 * 获取记录总数
	 * 
	 * @author songkun
	 * @create 2014年5月3日 下午3:59:28
	 * @since
	 * @return
	 */
	public int getTotal();
}
