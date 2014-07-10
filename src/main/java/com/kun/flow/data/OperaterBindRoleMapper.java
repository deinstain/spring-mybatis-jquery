package com.kun.flow.data;

import java.io.Serializable;

import org.apache.ibatis.annotations.Param;

import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.OperaterBindRole;

public interface OperaterBindRoleMapper extends IMapper<OperaterBindRole> {

	/**
	 * 删除操作者对应的角色
	 * 
	 * @author songkun
	 * @create 2014年4月25日 下午10:15:25
	 * @param key
	 * @throws ServiceException
	 */
	public void deleteByOperater(@Param("key") Serializable key) throws ServiceException;
}