package com.kun.flow.data;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kun.flow.bean.Pagination;
import com.kun.flow.model.RoleBindPermit;

public interface RoleBindPermitMapper extends IMapper<RoleBindPermit> {

	/**
	 * 删除绑定到角色的关联
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午10:26:31
	 * @since
	 * @param key
	 * @throws Exception
	 */
	public void deleteByRole(@Param("key") Serializable key) throws Exception;

	/**
	 * 获取绑定到角色上的权限列表
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午10:59:42
	 * @since
	 * @param key
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<RoleBindPermit> listByRole(@Param("key") Serializable key, @Param("page") Pagination page) throws Exception;

	/**
	 * 获取绑定到角色上的权限个数
	 * 
	 * @author songkun
	 * @create 2014年5月4日 下午10:57:55
	 * @since
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public int getCountByRole(@Param("key") Serializable key) throws Exception;
}