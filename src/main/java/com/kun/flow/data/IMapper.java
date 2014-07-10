/**
 * Program  : IMapper.java
 * Author   : songkun
 * Create   : 2014年4月23日 下午2:16:30
 * 
 */

package com.kun.flow.data;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kun.flow.bean.Pagination;
import com.kun.flow.exception.DataException;

/**
 * 通用接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月23日 下午2:16:30
 */
public interface IMapper<T> {
	/**
	 * 新增对象
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:12:27
	 * @since
	 * @param object
	 * @throws DataException
	 * @throws Exception
	 */
	public void save(@Param("object") T object) throws Exception;

	/**
	 * 修改对象
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:12:41
	 * @since
	 * @param object
	 * @throws DataException
	 * @throws Exception
	 */
	public void update(@Param("object") T object) throws Exception;

	/**
	 * 删除对象
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:13:05
	 * @since
	 * @param object
	 * @throws DataException
	 * @throws Exception
	 */
	public void delete(@Param("object") T object) throws Exception;

	/**
	 * 删除所有相关记录
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:13:17
	 * @since
	 * @throws DataException
	 * @throws Exception
	 */
	public void deleteAll() throws Exception;

	/**
	 * 根据主键,删除对象
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:13:36
	 * @since
	 * @param key
	 * @throws DataException
	 * @throws Exception
	 */
	public void deleteByKey(@Param("key") Serializable key) throws Exception;

	/**
	 * 根据主键,获得对象
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:13:45
	 * @since
	 * @param key
	 * @return
	 * @throws DataException
	 * @throws Exception
	 */
	public T getByKey(@Param("key") Serializable key) throws Exception;

	/**
	 * 根据实例,获得对象集
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:13:52
	 * @since
	 * @param object
	 * @return
	 * @throws DataException
	 * @throws Exception
	 */
	public List<T> findByExample(@Param("object") T object) throws Exception;

	/**
	 * 根据实例,分页获得对象集,不支持like查找,只能精确查找
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:14:08
	 * @since
	 * @param object
	 * @param page
	 * @return
	 * @throws DataException
	 * @throws Exception
	 */
	public List<T> findOnePageByExample(@Param("object") T object, @Param("page") Pagination page)
			throws Exception;

	/**
	 * 根据实例,获得对象
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:14:16
	 * @since
	 * @param object
	 * @return
	 * @throws DataException
	 * @throws Exception
	 */
	public T findOneByExample(@Param("object") T object) throws Exception;

	/**
	 * 加载所有对象
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:14:24
	 * @since
	 * @return
	 * @throws DataException
	 * @throws Exception
	 */
	public List<T> loadAll() throws Exception;

	/**
	 * 获得当前页的对象(没有设置查询条件)
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:14:31
	 * @since
	 * @param page
	 * @return
	 * @throws DataException
	 * @throws Exception
	 */
	public List<T> loadOnePage(@Param("page") Pagination page) throws Exception;

	/**
	 * 根据实例,分页获得对象集,如果字段是字符串,则使用模糊查找
	 * 
	 * @author songkun
	 * @create 2014年4月23日 下午7:14:38
	 * @since
	 * @param object
	 * @param page
	 * @return
	 * @throws DataException
	 * @throws Exception
	 */
	public List<T> search(@Param("object") T object, @Param("page") Pagination page) throws Exception;

	/**
	 * 根据实例,获得记录条数,不支持like查找,只能精确查找.一般与findOnePageByExample一起使用
	 * 
	 * @author songkun
	 * @create 2014年5月4日 下午5:30:25
	 * @since
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public int getCountByExample(@Param("object") T object) throws Exception;

	/**
	 * 获得总记录条数,一般与loadOnePage一起使用
	 * 
	 * @author songkun
	 * @create 2014年5月4日 下午5:31:28
	 * @since
	 * @return
	 * @throws Exception
	 */
	public int getCount() throws Exception;

	/**
	 * 根据实例,获得记录条数,如果字段是字符串,则使用模糊查找(一般与search一起使用)
	 * 
	 * @author songkun
	 * @create 2014年5月4日 下午5:33:13
	 * @since
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public int getCountForSearch(@Param("object") T object) throws Exception;

}
