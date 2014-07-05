/**
 * Program  : IRoleBindPermitService.java
 * Author   : songkun
 * Create   : 2014年4月26日 上午10:21:01
 *
 */

package com.kun.flow.service;

import java.util.List;

import com.kun.flow.bean.Pagination;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Operater;

/**
 * 角色绑定到权限的业务接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月26日 上午10:21:01
 */
public interface IRoleBindPermitService extends IService {

	/**
	 * 修改角色绑定权限
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午10:22:12
	 * @since
	 * @param roleId
	 * @param ids
	 * @param operater
	 * @throws ServiceException
	 */
	public void updateBindsByRole(Long roleId, String ids, Operater operater) throws ServiceException;

	/**
	 * 获取绑定到角色上的权限
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午10:57:01
	 * @param roleId
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	public List<Object> listByRole(Long roleId, Pagination page) throws ServiceException;
}
