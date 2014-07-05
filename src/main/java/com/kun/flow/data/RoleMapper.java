package com.kun.flow.data;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kun.flow.bean.Pagination;

public interface RoleMapper extends IMapper {

	/**
	 * 获取绑定到操作者上的角色
	 * 
	 * @author songkun
	 * @create 2014年4月25日 下午9:08:44
	 * @param key
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<Object> listByOperater(@Param("key")
	Serializable key, @Param("page")
	Pagination page) throws Exception;

	/**
	 * 获取还未被该用户绑定的角色列表
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午9:19:41
	 * @param key
	 * @throws Exception
	 */
	public List<Object> listUnbindRolesByOperater(@Param("key")
	Serializable key) throws Exception;
}