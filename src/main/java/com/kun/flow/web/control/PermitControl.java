/**
 * Program  : PermitAction.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午2:03:25
 *
 */

package com.kun.flow.web.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kun.flow.bean.Pagination;
import com.kun.flow.bean.TreeNode;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Operater;
import com.kun.flow.model.Permit;
import com.kun.flow.service.IPermitService;
import com.kun.flow.web.response.DataOut;
import com.kun.flow.web.response.Out;

/**
 * 权限处理
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午2:03:25
 */
@RequestMapping("/permit")
public class PermitControl extends BaseControl<Permit> {

	public IPermitService getPermitService() {
		return (IPermitService) this.getService();
	}

	/**
	 * 查询用户权限列表
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:49:06
	 * @since
	 * @return
	 */
	@RequestMapping("/listMy.do")
	@ResponseBody
	public Out<TreeNode> listMy() {
		Operater operater = this.getCurrentOperater();
		try {
			if (operater.getSuperUser() == 1) {
				return this.toTree(this.getService().loadAll(), false);
			} else {
				return this.toTree(this.getPermitService().listPermitByOperater(operater.getId()), false);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取权限列表,角色绑定权限时 使用
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:50:07
	 * @since
	 * @return
	 */
	@RequestMapping("/getPermitTreeJson.do")
	@ResponseBody
	public Out<TreeNode> getPermitTreeJson() {
		try {
			return this.toTree(this.getService().loadAll(), true);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据角色ID列出已绑定的权限列表
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:49:56
	 * @since
	 * @return
	 */
	@RequestMapping("/listBindedByRole.do")
	@ResponseBody
	public Out<TreeNode> listBindedByRole(Permit permit) {
		try {
			return this.toTree(this.getPermitService().listPermitsByRole(permit.getRoleId()), true);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回树形权限列表(左边列表，绑定界面中的列表)
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:50:44
	 * @since
	 * @param list
	 * @param useId
	 * @return
	 */
	private Out<TreeNode> toTree(List<Permit> list, boolean useId) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		if (list == null || list.size() == 0) {
			return null;
		}
		TreeNode treeNode, childNode;
		for (Iterator<Permit> iterator = list.iterator(); iterator.hasNext();) {
			Permit tn = (Permit) iterator.next();
			if (tn.getLeaf() == 0) {// 非叶子节点
				treeNode = new TreeNode();
				if (useId) {
					treeNode.setId(tn.getId().toString());
				} else {
					treeNode.setId(tn.getCode());
				}
				treeNode.setText(tn.getName());
				for (Iterator<Permit> ite = list.iterator(); ite.hasNext();) {
					Permit tn2 = (Permit) ite.next();
					if (!tn2.getCode().equals(tn.getCode()) && tn2.getCode().startsWith(tn.getCode())) {
						childNode = new TreeNode();
						if (useId) {
							childNode.setId(tn2.getId().toString());
						} else {
							childNode.setId(tn2.getCode());
						}
						childNode.setText(tn2.getName());
						// childNode.setLeaf(true);
						treeNode.addToChildNodes(childNode);
					}
				}
				treeNodes.add(treeNode);
			}
		}
		Pagination pagination = new Pagination();
		pagination.setTotalRows(treeNodes.size());
		return new DataOut<TreeNode>(treeNodes, pagination);
	}
}
