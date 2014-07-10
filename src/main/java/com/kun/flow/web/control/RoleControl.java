/**
 * Program  : RoleAction.java
 * Author   : songkun
 * Create   : 2014年4月26日 上午9:52:17
 *
 */

package com.kun.flow.web.control;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kun.flow.bean.Pagination;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Role;
import com.kun.flow.service.IRoleService;
import com.kun.flow.web.response.DataOut;
import com.kun.flow.web.response.MessageOut;
import com.kun.flow.web.response.Out;

/**
 * 角色处理
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月26日 上午9:52:17
 */
@RequestMapping("/role")
public class RoleControl extends BaseControl<Role> {

	public IRoleService getRoleService() {
		return (IRoleService) this.getService();
	}

	/**
	 * 获取角色列表
	 * 
	 * @author songkun
	 * @return
	 * @return String
	 * @date 2011-6-17 下午05:32:08
	 * @since 2.0.0
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public Out<Role> list(Pagination pagination) {
		try {
			return new DataOut<Role>(this.getService().loadOnePage(pagination), pagination);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增角色
	 * 
	 * @author songkun
	 * @return Out
	 * @date 2011-6-17 下午05:32:33
	 * @since 2.0.0
	 */
	@RequestMapping("/add.do")
	@ResponseBody
	public Out<Object> add(Role role) {
		this.getLogger().info("新增用户: " + role.getName());
		try {
			if (this.getRoleService().isExist(role)) {// 如果后台用户已经存在
				return MessageOut.NAME_EXIST_MESSAGE;
			} else {
				Date date = new Date();
				role.setCreateTime(date);
				role.setUpdateTime(date);
				role.setOperaterId(this.getCurrentOperater().getId());
				role.setOperaterCode(this.getCurrentOperater().getCode());
				role.setStatus(1);
				this.getService().save(role);
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
	 * 修改角色
	 * 
	 * @author songkun
	 * @return
	 * @return String
	 * @date 2011-6-19 上午11:51:48
	 * @since 2.0.0
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Out<Object> update(Role role) {
		try {
			Role dbRole = (Role) getRoleService().getByKey(role.getId());
			if (dbRole == null) {
				return MessageOut.RECORD_UN_EXIST_MESSAGE;
			} else {
				if (getRoleService().isExist(role)) {
					return MessageOut.NAME_EXIST_MESSAGE;
				} else {
					dbRole.setName(role.getName());
					dbRole.setUpdateTime(new Date());
					dbRole.setRemark(role.getRemark());
					dbRole.setOperaterId(this.getCurrentOperater().getId());
					dbRole.setOperaterCode(this.getCurrentOperater().getCode());
					this.getService().update(dbRole);
				}
			}
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
	 * 删除后台用户
	 * 
	 * @author songkun
	 * @return String
	 * @date 2011-6-17 下午05:32:25
	 * @since 2.0.0
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Out<Object> delete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idsArr = ids.split(",");
				for (int i = 0; i < idsArr.length; i++) {
					this.getService().deleteByKey(Long.parseLong(idsArr[i]));
				}
			}
			return MessageOut.DELETE_OK_MESSAGE;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MessageOut.DELETE_FAIL_MESSAGE;
	}

	/**
	 * 查询
	 * 
	 * @author songkun
	 * @return String
	 * @date 2011-6-19 下午05:44:22
	 * @since 2.0.0
	 */
	@RequestMapping("/search.do")
	@ResponseBody
	public Out<Role> search(Role role, Pagination pagination) {
		try {
			return new DataOut<Role>(this.getService().search(role, pagination), pagination);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
