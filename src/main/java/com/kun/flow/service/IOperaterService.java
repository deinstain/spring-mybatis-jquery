/**
 * Program  : IOperaterService.java
 * Author   : songkun
 * Create   : 2014年4月24日 下午11:35:59
 *
 */

package com.kun.flow.service;

import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Operater;

/**
 * 操作员业务处理接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月24日 下午11:35:59
 */
public interface IOperaterService extends IService<Operater> {

	/**
	 * 登录验证
	 * 
	 * @author songkun
	 * @create 2014年4月24日 下午11:47:04
	 * @since
	 * @param operater
	 * @return
	 * @throws ServiceException
	 */
	public Operater validate(Operater operater) throws ServiceException;

	/**
	 * 判断用户是否已经存在
	 * 
	 * @author songkun
	 * @create 2014年4月24日 下午11:47:11
	 * @since
	 * @param operater
	 * @return
	 * @throws ServiceException
	 */
	public boolean isExist(Operater operater) throws ServiceException;

}
