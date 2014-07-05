/**
 * Program  : OperaterBindRoleServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午9:34:58
 *
 */

package com.kun.flow.service.impl;

import java.util.Date;

import com.kun.flow.data.OperaterBindRoleMapper;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Operater;
import com.kun.flow.model.OperaterBindRole;
import com.kun.flow.service.IOperaterBindRoleService;

/**
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午9:34:58
 */
public class OperaterBindRoleServiceImpl extends AbstractServiceImpl implements IOperaterBindRoleService {

	private OperaterBindRoleMapper getOperaterBindRoleMapper() {
		return (OperaterBindRoleMapper) this.getMapper();
	}

	@Override
	public void updateBindsByOperater(Long userId, String ids, Operater operater) throws ServiceException {
		// 删除所有已经绑定到该后台用户的记录
		this.getOperaterBindRoleMapper().deleteByOperater(userId);
		// 新增新的绑定信息
		if (ids != null && ids.length() > 0) {
			String[] idsStr = ids.split(",");
			OperaterBindRole obr;
			Date date = new Date();
			for (int i = 0; i < idsStr.length; i++) {
				obr = new OperaterBindRole();
				obr.setOperaterCode(operater.getOperaterCode());
				obr.setOperaterId(operater.getId());
				obr.setUserId(userId);
				obr.setRoleId(Long.parseLong(idsStr[i]));
				obr.setCreateTime(date);
				obr.setUpdateTime(date);
				obr.setStatus(1);
				this.save(obr);
			}
		}
	}

}
