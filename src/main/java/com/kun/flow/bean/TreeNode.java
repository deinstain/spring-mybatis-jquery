package com.kun.flow.bean;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	// 节点id
	private String id;
	// 节点名称
	private String text;
	// 是否是子节点
//	private boolean leaf;

	// 子节点
	private List<TreeNode> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

//	public boolean isLeaf() {
//		return leaf;
//	}
//
//	public void setLeaf(boolean leaf) {
//		this.leaf = leaf;
//	}

	/**
	 * 添加子节点
	 * 
	 * @author songkun
	 * @param childNode
	 * @return void
	 * @date 2011-6-3 下午04:29:08
	 * @since 2.0.0
	 */
	public void addToChildNodes(TreeNode childNode) {
		if (this.children == null) {
			this.children = new ArrayList<TreeNode>();
		}
		this.children.add(childNode);
	}
}
