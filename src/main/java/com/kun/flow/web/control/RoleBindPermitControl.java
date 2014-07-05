/**
 * Program  : RoleBindPermitAction.java
 * Author   : songkun
 * Create   : 2014年4月26日 上午10:39:47
 *
 */

package com.kun.flow.web.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kun.flow.bean.Pagination;
import com.kun.flow.bean.TreeGridNode;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Permit;
import com.kun.flow.model.RoleBindPermit;
import com.kun.flow.service.IRoleBindPermitService;
import com.kun.flow.web.response.DataOut;
import com.kun.flow.web.response.MessageOut;
import com.kun.flow.web.response.Out;

/**
 * 角色-权限 处理类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月26日 上午10:39:47
 */
@RequestMapping("/roleBindPermit")
public class RoleBindPermitControl extends BaseControl {

	public IRoleBindPermitService getRoleBindPermitService() {
		return (IRoleBindPermitService) this.getService();
	}

	/**
	 * 获取已绑定的权限列表
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午10:41:34
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public Out list(RoleBindPermit rbp, Pagination pagination) {
		try {
			return toTreeGrid(this.getRoleBindPermitService().listByRole(rbp.getRole().getId(), pagination));
		} catch (ServiceException ex) {
			getLogger().error(ex.getMessage(), ex);
		} catch (Exception ex) {
			getLogger().error(ex.getMessage(), ex);
		}
		return null;
	}

	/**
	 * 更新角色绑定的权限
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午10:42:27
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Out update(RoleBindPermit rbp, String ids) {
		try {
			this.getRoleBindPermitService().updateBindsByRole(rbp.getRole().getId(), ids, this.getCurrentOperater());
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException ex) {
			getLogger().error(ex.getMessage(), ex);
		} catch (Exception ex) {
			getLogger().error(ex.getMessage(), ex);
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;
	}

	/**
	 * 返回树形权限列表(左边列表，绑定界面中的列表)
	 * 
	 * @author songkun
	 * @create 2014年4月26日 上午10:42:37
	 */
	private Out toTreeGrid(List<Object> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		TreeGridNode treeNode, childNode;
		List<Object> treeNodes = new ArrayList<Object>();
		for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
			RoleBindPermit rbp = (RoleBindPermit) iterator.next();
			Permit tn = (Permit) rbp.getPermit();
			if (tn.getLeaf() == 0) {// 非叶子节点
				treeNode = new TreeGridNode();
				treeNode.setId(tn.getId());
				treeNode.setName(tn.getName());
				treeNode.setOperaterCode(rbp.getOperaterCode());
				treeNode.setCreateTime(rbp.getCreateTime());
				treeNode.setCls("bind-folder");
				treeNode.setLeaf(false);
				treeNode.setSingleClickExpand(true);
				for (Iterator<Object> ite = list.iterator(); ite.hasNext();) {
					RoleBindPermit rbp2 = (RoleBindPermit) ite.next();
					Permit tn2 = (Permit) rbp2.getPermit();
					if (!tn2.getCode().equals(tn.getCode()) && tn2.getCode().startsWith(tn.getCode())) {
						childNode = new TreeGridNode();
						childNode.setId(tn2.getId());
						childNode.setName(tn2.getName());
						childNode.setOperaterCode(rbp2.getOperaterCode());
						childNode.setCreateTime(rbp2.getCreateTime());
						childNode.setCls("file");
						childNode.setIconCls("file-icon");
						treeNode.addToChildren(childNode);
					}
				}
				treeNodes.add(treeNode);
			}
		}
		Pagination pagination = new Pagination();
		pagination.setTotalRows(treeNodes.size());
		return new DataOut(treeNodes, pagination);
	}
}
