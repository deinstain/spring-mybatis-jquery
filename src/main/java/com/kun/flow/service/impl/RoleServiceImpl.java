/**
 * Program  : RoleServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午9:12:29
 *
 */

package com.kun.flow.service.impl;

import java.io.Serializable;
import java.util.List;

import com.kun.flow.bean.Pagination;
import com.kun.flow.data.RoleBindPermitMapper;
import com.kun.flow.data.RoleMapper;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Role;
import com.kun.flow.service.IRoleService;

/**
 * 角色业务实现类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午9:12:29
 */
public class RoleServiceImpl extends AbstractServiceImpl implements IRoleService {

	private RoleBindPermitMapper roleBindPermitMapper;

	public RoleMapper getRoleMapper() {
		return (RoleMapper) this.getMapper();
	}

	public RoleBindPermitMapper getRoleBindPermitMapper() {
		return roleBindPermitMapper;
	}

	public void setRoleBindPermitMapper(RoleBindPermitMapper roleBindPermitMapper) {
		this.roleBindPermitMapper = roleBindPermitMapper;
	}

	@Override
	public List<Object> listByOperater(Serializable key, Pagination page) throws ServiceException {
		try {
			return this.getRoleMapper().listByOperater(key, page);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Object> listUnbindRolesByOperater(Serializable key) throws ServiceException {
		try {
			return this.getRoleMapper().listUnbindRolesByOperater(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isExist(Role role) throws ServiceException {
		Role tmp = new Role();
		tmp.setName(role.getName());
		tmp = (Role) findOneByExample(tmp);
		return (tmp != null) && !(tmp.getId().equals(role.getId()));
	}

	@Override
	public void deleteByKey(Serializable key) throws ServiceException {
		try {
			// 笨拙的级联删除
			this.getRoleBindPermitMapper().deleteByRole(key);
			super.deleteByKey(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
