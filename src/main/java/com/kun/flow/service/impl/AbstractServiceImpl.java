/**
 * Program  : AbstractServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月23日 下午2:35:53
 *
 */

package com.kun.flow.service.impl;

import java.io.Serializable;
import java.util.List;

import com.kun.flow.bean.Pagination;
import com.kun.flow.data.IMapper;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.service.IService;

/**
 * 实现通用接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月23日 下午2:35:53
 */
public abstract class AbstractServiceImpl implements IService {

	private IMapper mapper;

	public IMapper getMapper() {
		return mapper;
	}

	public void setMapper(IMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void save(Object object) throws ServiceException {
		try {
			mapper.save(object);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void update(Object object) throws ServiceException {
		try {
			mapper.update(object);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Object getByKey(Serializable key) throws ServiceException {
		try {
			return mapper.getByKey(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Object object) throws ServiceException {
		try {
			mapper.delete(object);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteAll() throws ServiceException {
		try {
			mapper.deleteAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteByKey(Serializable key) throws ServiceException {
		try {
			mapper.deleteByKey(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Object> findByExample(Object object) throws ServiceException {
		try {
			return mapper.findByExample(object);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Object findOneByExample(Object object) throws ServiceException {
		try {
			return mapper.findOneByExample(object);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Object> loadAll() throws ServiceException {
		try {
			return mapper.loadAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Object> findOnePageByExample(Object object, Pagination page) throws ServiceException {
		try {
			List<Object> list = mapper.findOnePageByExample(object, page);
			// 如果记录数不够，则不用查询数据，直接计算出记录条数，前提：页面不能提交错误的pageNumber
			if (list == null || list.size() < page.getPageSize()) {
				page.setTotalRows((page.getPageNumber() - 1) * page.getPageSize() + (list == null ? 0 : list.size()));
			} else {
				page.setTotalRows(mapper.getCountByExample(object));
			}
			return list;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Object> loadOnePage(Pagination page) throws ServiceException {
		try {
			List<Object> list = mapper.loadOnePage(page);
			// 如果记录数不够，则不用查询数据，直接计算出记录条数，前提：页面不能提交错误的pageNumber
			if (list == null || list.size() < page.getPageSize()) {
				page.setTotalRows((page.getPageNumber() - 1) * page.getPageSize() + (list == null ? 0 : list.size()));
			} else {
				page.setTotalRows(mapper.getCount());
			}
			return list;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public List<Object> search(Object object, Pagination page) throws ServiceException {
		try {
			List<Object> list = mapper.search(object, page);
			// 如果记录数不够，则不用查询数据，直接计算出记录条数，前提：页面不能提交错误的pageNumber
			if (list == null || list.size() < page.getPageSize()) {
				page.setTotalRows((page.getPageNumber() - 1) * page.getPageSize() + (list == null ? 0 : list.size()));
			} else {
				page.setTotalRows(mapper.getCountForSearch(object));
			}
			return list;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
