/**
 * Program  : IRole.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午9:10:16
 *
 */

package com.kun.flow.service;

import java.io.Serializable;
import java.util.List;

import com.kun.flow.bean.Pagination;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Role;

/**
 * 角色业务处理接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午9:10:16
 */
public interface IRoleService extends IService {

	/**
	 * 获取绑定到操作者上的角色
	 * 
	 * @author songkun
	 * @create 2014年4月25日 下午9:11:46
	 * @since
	 * @param key
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<Object> listByOperater(Serializable key, Pagination page) throws ServiceException;

	/**
	 * 获取还未被该用户绑定的角色列表
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午9:19:41
	 * @param key
	 * @return
	 * @throws ServiceException
	 */
	public List<Object> listUnbindRolesByOperater(Serializable key) throws ServiceException;

	/**
	 * 判断角色是否存在
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午9:59:05
	 * @param role
	 * @return
	 * @throws ServiceException
	 */
	public boolean isExist(Role role) throws ServiceException;
}
