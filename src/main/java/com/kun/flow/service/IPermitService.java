/**
 * Program  : IPermitService.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午2:00:58
 *
 */

package com.kun.flow.service;

import java.io.Serializable;
import java.util.List;

import com.kun.flow.exception.ServiceException;

/**
 * 权限业务接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午2:00:58
 */
public interface IPermitService extends IService {

	/**
	 * 获取已被该角色绑定的权限列表
	 * 
	 * @author songkun
	 * @date Sep 14, 2010 4:21:27 PM
	 * @return DataPage
	 * @param key
	 * @return
	 * @throws ServiceException
	 */
	public List<Object> listPermitsByRole(Serializable key) throws ServiceException;

	/**
	 * 获取还未被该角色绑定的权限列表
	 * 
	 * @author songkun
	 * @date Sep 14, 2010 10:27:31 AM
	 * @return DataPage
	 * @param key
	 * @return
	 * @throws ServiceException
	 */
	public List<Object> listUnbindPermitsByRole(Serializable key) throws ServiceException;

	/**
	 * 获取用户的权限列表
	 * 
	 * @author songkun
	 * @date Oct 21, 2010 8:43:10 AM
	 * @return List
	 * @param key
	 * @return
	 * @throws ServiceException
	 */
	public List<Object> listPermitByOperater(Serializable key) throws ServiceException;
}
