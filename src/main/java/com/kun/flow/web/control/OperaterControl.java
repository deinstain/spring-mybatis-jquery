/**
 * Program  : OperaterAction.java
 * Author   : songkun
 * Create   : 2014年4月24日 下午11:32:42
 *
 */

package com.kun.flow.web.control;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kun.flow.bean.Pagination;
import com.kun.flow.constants.Constants;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Operater;
import com.kun.flow.service.IOperaterService;
import com.kun.flow.util.MD5Util;
import com.kun.flow.web.response.DataOut;
import com.kun.flow.web.response.MessageOut;
import com.kun.flow.web.response.Out;

/**
 * 后台操作者action
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月24日 下午11:32:42
 */
@Controller
@RequestMapping("/operater")
public class OperaterControl extends BaseControl {

	public IOperaterService getOperaterService() {
		return (IOperaterService) this.getService();
	}

	/**
	 * 获取后台用户列表
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param pagination
	 * @return Out
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public Out list(Pagination pagination) {
		try {
			return new DataOut(this.getService().loadOnePage(pagination), pagination);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param operater
	 * @return Out
	 */
	@RequestMapping("/add.do")
	@ResponseBody
	public Out add(Operater operater) {
		try {
			if (this.getOperaterService().isExist(operater)) {// 如果后台用户已经存在
				return MessageOut.NAME_OR_CODE_EXIST_MESSAGE;
			} else {
				this.getLogger().info("新增用户: " + operater.getName());
				operater.setPassword(MD5Util.getMD5String(operater.getPassword()));
				operater.setSuperUser(0);
				Date date = new Date();
				operater.setCreateTime(date);
				operater.setUpdateTime(date);
				operater.setOperaterId(this.getCurrentOperater().getId());
				operater.setOperaterCode(this.getCurrentOperater().getCode());
				operater.setStatus(1);
				this.getService().save(operater);
			}
			return MessageOut.ADD_OK_MESSAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.ADD_FAIL_MESSAGE;
	}

	/**
	 * 修改后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param operater
	 * @return Out
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Out update(Operater operater) {
		Operater curOperater = this.getCurrentOperater();
		try {
			Operater dbOperater = (Operater) getOperaterService().getByKey(operater.getId());
			if (dbOperater == null) {
				return MessageOut.RECORD_UN_EXIST_MESSAGE;
			} else {
				// 如果不是超级用户 或者 不是用户自己 ，无法修改
				if (dbOperater.getId().equals(curOperater.getSuperUser())
						|| (curOperater.getSuperUser() == 1 && dbOperater.getSuperUser() != 1)) {
					if (getOperaterService().isExist(operater)) {
						return MessageOut.NAME_OR_CODE_EXIST_MESSAGE;
					} else {
						dbOperater.setCode(operater.getCode());
						dbOperater.setName(operater.getName());
						dbOperater.setEmail(operater.getEmail());
						dbOperater.setType(operater.getType());
						if (operater.getPassword() != null && operater.getPassword().length() > 0) {
							dbOperater.setPassword(MD5Util.getMD5String(operater.getPassword()));
						}
						dbOperater.setPhone(operater.getPhone());
						dbOperater.setUpdateTime(new Date());
						dbOperater.setOperaterId(curOperater.getId());
						dbOperater.setOperaterCode(curOperater.getCode());
						this.getService().update(dbOperater);
					}
				} else {
					return MessageOut.NO_PERMIT_UPDATE_MESSAGE;
				}
			}
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;
	}
	/**
	 * 删除后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param ids
	 * @return Out
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Out delete(String ids) {
		try {
			boolean flag = true;
			String msg = null;
			if (ids != null && ids.length() > 0) {
				String[] idsArr = ids.split(",");
				for (int i = 0; i < idsArr.length; i++) {
					Operater operater = (Operater) this.getService().getByKey(Long.parseLong(idsArr[i]));
					if (1 == operater.getSuperUser()) {
						flag = false;
						continue;
					}
					this.getService().delete(operater);
				}
				if (!flag) {
					if (idsArr.length == 1) {
						msg = "超级用户不能删除";
					} else {
						msg = "选中的超级用户不能删除";
					}
				}
			}
			if (flag) {
				return MessageOut.DELETE_OK_MESSAGE;
			} else {
				return new MessageOut(false, msg);
				// this.modelWrapper.setMessage(true, msg);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.DELETE_FAIL_MESSAGE;
	}

	/**
	 * 查询
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param operater
	 * @param pagination
	 * @return Out
	 */
	@RequestMapping("/search.do")
	@ResponseBody
	public Out search(Operater operater, Pagination pagination) {
		try {
			return new DataOut(this.getService().search(operater, pagination), pagination);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * 获取我的信息
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @return Operater
	 */
	@RequestMapping("/getMyInfo.do")
	@ResponseBody
	public Operater getMyInfo() {
		Operater operater = new Operater();
		operater.setCode(this.getCurrentOperater().getCode());
		operater.setEmail(this.getCurrentOperater().getEmail());
		operater.setName(this.getCurrentOperater().getName());
		operater.setPhone(this.getCurrentOperater().getPhone());
		return operater;
	}

	/**
	 * 修改我的信息
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param operater
	 * @return Out
	 */
	@RequestMapping("/updateMyInfo.do")
	@ResponseBody
	public Out updateMyInfo(Operater operater) {
		try {
			Operater dbOperater = (Operater) this.getService().getByKey(this.getCurrentOperater().getId());
			if (dbOperater == null) {
				return MessageOut.UPDATE_FAIL_MESSAGE;
			}
			dbOperater.setEmail(operater.getEmail());
			dbOperater.setName(operater.getName());
			dbOperater.setPhone(operater.getPhone());
			dbOperater.setOperaterCode(dbOperater.getCode());
			dbOperater.setOperaterId(dbOperater.getId());
			dbOperater.setUpdateTime(new Date());
			if (operater.getPassword() != null && operater.getPassword().length() > 0) {
				dbOperater.setPassword(MD5Util.getMD5String(operater.getPassword()));
			}
			this.getService().update(dbOperater);
			((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(true)
					.setAttribute(Constants.USER_INFO, dbOperater);
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;
	}

	/**
	 * 禁用后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param ids
	 * @return Out
	 */
	@RequestMapping("/disable.do")
	@ResponseBody
	public Out disable(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] tmp = ids.split(",");
				for (int i = 0; i < tmp.length; i++) {
					Operater dbOperater = (Operater) this.getService().getByKey(Long.parseLong(tmp[i]));
					dbOperater.setStatus(0);
					dbOperater.setOperaterId(this.getCurrentOperater().getId());
					dbOperater.setOperaterCode(this.getCurrentOperater().getCode());
					dbOperater.setUpdateTime(new Date());
					this.getService().update(dbOperater);
				}
			}
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;
	}

	/**
	 * 启用后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param ids
	 * @return Out
	 */
	@RequestMapping("/enable.do")
	@ResponseBody
	public Out enable(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] tmp = ids.split(",");
				for (int i = 0; i < tmp.length; i++) {
					Operater dbOperater = (Operater) this.getService().getByKey(Long.parseLong(tmp[i]));
					dbOperater.setStatus(1);
					dbOperater.setOperaterId(this.getCurrentOperater().getId());
					dbOperater.setOperaterCode(this.getCurrentOperater().getCode());
					dbOperater.setUpdateTime(new Date());
					this.getService().update(dbOperater);
				}
			}
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;
	}
}
