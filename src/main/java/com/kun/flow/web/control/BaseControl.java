/**
 * Program  : BaseControl.java
 * Author   : songkun
 * Create   : 2014年6月28日 上午9:51:14
 *
 */

package com.kun.flow.web.control;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kun.flow.constants.Constants;
import com.kun.flow.model.Operater;
import com.kun.flow.service.IService;

/**
 * 控制层基类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年6月28日 上午9:51:14
 */
public abstract class BaseControl {

	/**
	 * service 接口
	 */
	private IService service;

	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}

	/**
	 * 获取当前登录的用户信息
	 * 
	 * @author songkun
	 * @create 2014年6月28日 上午11:02:11
	 * @return Operater
	 */
	protected Operater getCurrentOperater() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		if (request.getSession() == null) {
			return null;
		}
		return (Operater) request.getSession().getAttribute(Constants.USER_INFO);
	}

	/**
	 * 获取Logger
	 * 
	 * @author songkun
	 * @return
	 * @return Logger
	 * @date 2011-5-30 下午08:18:01
	 * @since 2.0.0
	 */
	protected final Logger getLogger() {
		return Logger.getLogger(this.getClass());
	}
}
