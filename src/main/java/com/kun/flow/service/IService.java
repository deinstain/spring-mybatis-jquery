package com.kun.flow.service;

import java.io.Serializable;
import java.util.List;

import com.kun.flow.bean.Pagination;
import com.kun.flow.exception.ServiceException;

/**
 * service 接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月23日 下午2:34:55
 */
public interface IService<T> {

	/**
	 * 保存对象
	 * 
	 * @param object
	 */
	public void save(T object) throws ServiceException;

	/**
	 * 更新对象
	 * 
	 * @param object
	 */
	public void update(T object) throws ServiceException;

	/**
	 * 根据主键,获得对象
	 * 
	 * @param key
	 * @return
	 */
	public T getByKey(Serializable key) throws ServiceException;

	/**
	 * 删除对象
	 * 
	 * @param object
	 */
	public void delete(T object) throws ServiceException;

	/**
	 * 删除对象所有相关记录
	 * 
	 */
	public void deleteAll() throws ServiceException;

	/**
	 * 根据主键,删除对象
	 * 
	 * @param key
	 */
	public void deleteByKey(Serializable key) throws ServiceException;

	/**
	 * 根据实例,获得对象集
	 * 
	 * @param object
	 */
	public List<T> findByExample(T object) throws ServiceException;

	/**
	 * 根据实例,分页获得对象集,不支持like查找,只能精确查找
	 * 
	 * @author songkun
	 * @create 2014年5月3日 下午10:27:12
	 * @param object
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	public List<T> findOnePageByExample(T object, Pagination page) throws ServiceException;

	/**
	 * 根据实例,获得对象
	 * 
	 * @author songkun
	 * @create 2014年5月3日 下午10:27:23
	 * @since
	 * @param object
	 * @return
	 * @throws ServiceException
	 */
	public T findOneByExample(T object) throws ServiceException;

	/**
	 * 加载所有对象
	 * 
	 * @author songkun
	 * @create 2014年5月3日 下午10:27:36
	 * @since
	 * @return
	 * @throws ServiceException
	 */
	public List<T> loadAll() throws ServiceException;

	/**
	 * 获得当前页的对象(没有设置查询条件)
	 * 
	 * @author songkun
	 * @create 2014年5月3日 下午10:27:43
	 * @since
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	public List<T> loadOnePage(Pagination page) throws ServiceException;

	/**
	 * 根据实例,分页获得对象集,如果字段是字符串,则使用模糊查找
	 * 
	 * @author songkun
	 * @create 2014年5月3日 下午10:25:52
	 * @since
	 * @param object
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	public List<T> search(T object, Pagination page) throws ServiceException;

}
