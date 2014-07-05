/**
 * Program  : IOperaterBindRoleService.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午9:22:23
 *
 */

package com.kun.flow.service;

import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Operater;

/**
 * 操作者与角色绑定业务接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午9:22:23
 */
public interface IOperaterBindRoleService extends IService {
	/**
	 * 修改用户绑定角色
	 * 
	 * @author songkun
	 * @create 2014年4月25日 下午9:34:06
	 * @param userId
	 * @param ids
	 * @param operater
	 * @throws ServiceException
	 */
	public void updateBindsByOperater(Long userId, String ids, Operater operater) throws ServiceException;
}
