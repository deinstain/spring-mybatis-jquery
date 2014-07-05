/**
 * Program  : PermitServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午2:01:44
 *
 */

package com.kun.flow.service.impl;

import java.io.Serializable;
import java.util.List;

import com.kun.flow.data.PermitMapper;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.service.IPermitService;

/**
 * 权限业务处理实现类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午2:01:44
 */
public class PermitServiceImpl extends AbstractServiceImpl implements IPermitService {

	private PermitMapper getPermitMapper() {
		return (PermitMapper) this.getMapper();
	}
	@Override
	public List<Object> listPermitsByRole(Serializable key) throws ServiceException {
		try {
			return this.getPermitMapper().listPermitsByRole(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public List<Object> listUnbindPermitsByRole(Serializable key) throws ServiceException {
		try {
			return this.getPermitMapper().listUnbindPermitsByRole(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Object> listPermitByOperater(Serializable key) throws ServiceException {
		try {
			return this.getPermitMapper().listPermitsByOperater(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
