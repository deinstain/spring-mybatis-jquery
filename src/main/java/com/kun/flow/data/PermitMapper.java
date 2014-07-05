package com.kun.flow.data;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PermitMapper extends IMapper {

	/**
	 * 获取操作者的权限列表
	 * 
	 * @author songkun
	 * @create 2014年4月25日 下午3:06:24
	 * @since
	 * @param key
	 * @return List
	 * @throws Exception
	 */
	public List<Object> listPermitsByOperater(@Param("key")
	Serializable key) throws Exception;

	/**
	 * 获取角色的权限列表
	 * 
	 * @author songkun
	 * @create 2014年4月25日 下午3:07:58
	 * @since
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<Object> listPermitsByRole(@Param("key")
	Serializable key) throws Exception;

	/**
	 * 获取未绑定到角色上的权限
	 * 
	 * @author songkun
	 * @create 2014年4月25日 下午3:09:30
	 * @since
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<Object> listUnbindPermitsByRole(@Param("key")
	Serializable key) throws Exception;
}