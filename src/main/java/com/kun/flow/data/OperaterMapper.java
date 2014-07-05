package com.kun.flow.data;

import org.apache.ibatis.annotations.Param;

import com.kun.flow.model.Operater;

public interface OperaterMapper extends IMapper {

	/**
	 * 根据名称或账号获取操作者
	 * 
	 * @author songkun
	 * @create 2014年4月25日 下午6:49:59
	 * @since
	 * @param operater
	 * @throws Exception
	 */
	public Operater getOneByNameOrCode(@Param("object")
	Operater operater) throws Exception;
}