/**
 * Program  : RoleBindPermitServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月26日 上午10:23:45
 *
 */

package com.kun.flow.service.impl;

import java.util.Date;
import java.util.List;

import com.kun.flow.bean.Pagination;
import com.kun.flow.data.RoleBindPermitMapper;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Operater;
import com.kun.flow.model.Role;
import com.kun.flow.model.RoleBindPermit;
import com.kun.flow.service.IRoleBindPermitService;

/**
 * 角色-权限绑定 业务类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月26日 上午10:23:45
 */
public class RoleBindPermitServiceImpl extends AbstractServiceImpl implements IRoleBindPermitService {

	private RoleBindPermitMapper getRoleBindPermitMapper() {
		return (RoleBindPermitMapper) this.getMapper();
	}
	@Override
	public void updateBindsByRole(Long roleId, String ids, Operater operater) throws ServiceException {
		// 删除所有已经绑定到该后台用户的记录
		try {
			this.getRoleBindPermitMapper().deleteByRole(roleId);
			// 新增新的绑定信息
			if (ids != null && ids.length() > 0) {
				String[] idsStr = ids.split(",");
				RoleBindPermit rbp;
				Date date = new Date();
				Role role = new Role();
				role.setId(roleId);
				for (int i = 0; i < idsStr.length; i++) {
					rbp = new RoleBindPermit();
					rbp.setRoleId(roleId);
					rbp.setPermitId(Long.parseLong(idsStr[i]));
					rbp.setCreateTime(date);
					rbp.setUpdateTime(date);
					rbp.setOperaterCode(operater.getCode());
					rbp.setOperaterId(operater.getId());
					rbp.setStatus(1);
					this.save(rbp);
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Object> listByRole(Long roleId, Pagination page) throws ServiceException {
		try {
			List<Object> list = this.getRoleBindPermitMapper().listByRole(roleId, page);
			if (list == null || list.size() < page.getPageSize()) {
				page.setTotalRows((page.getPageNumber() - 1) * page.getPageSize() + (list == null ? 0 : list.size()));
			} else {
				page.setTotalRows(this.getRoleBindPermitMapper().getCountByRole(roleId));
			}
			return list;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
