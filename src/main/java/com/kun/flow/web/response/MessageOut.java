/**
 * Program  : Message.java
 * Author   : songkun
 * Create   : 2014年6月28日 下午12:53:10
 *
 */

package com.kun.flow.web.response;

import java.util.List;

/**
 * 文本消息输出类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年6月28日 下午12:53:10
 */
public class MessageOut<T> implements Out<T> {

	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 输出信息
	 */
	private String message;

	public MessageOut() {
	}

	public MessageOut(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	// 登录成功/失败 提示信息
	public static final MessageOut<Object> LOGIN_OK_MESSAGE = new MessageOut<Object>(true, "登录成功");
	public static final MessageOut<Object> LOGIN_FAIL_MESSAGE = new MessageOut<Object>(false, "登录失败,账号 或 密码 有误");
	// 登录成功/失败 提示信息
	// public static final String LOGIN_OK = "登录成功";
	// public static final String LOGIN_FAIL = "登录失败,账号 或 密码 有误";

	// 退出成功/失败 提示信息
	public static final MessageOut<Object> LOGOUT_OK_MESSAGE = new MessageOut<Object>(true, "退出成功");
	public static final MessageOut<Object> LOGOUT_FAIL_MESSAGE = new MessageOut<Object>(false, "退出失败,请刷新再试");
	// 退出成功/失败 提示信息
	// public static final String LOGOUT_OK = "退出成功";
	// public static final String LOGOUT_FAIL = "退出失败,请刷新再试";

	// 名称已存在 提示信息
	// public static final String NAME_EXIST = "此名称已存在,请确认";
	public static final MessageOut<Object> NAME_EXIST_MESSAGE = new MessageOut<Object>(false, "此名称已存在,请确认");
	public static final MessageOut<Object> NAME_OR_CODE_EXIST_MESSAGE = new MessageOut<Object>(false, "账号或名称已存在,请确认");
	// 记录已经删除 提示信息
	// public static final String RECORD_UN_EXIST = "此记录已删除,请刷新再试";
	public static final MessageOut<Object> RECORD_UN_EXIST_MESSAGE = new MessageOut<Object>(false, "此记录已删除,请刷新再试");
	// 无权修改他人信息
	public static final MessageOut<Object> NO_PERMIT_UPDATE_MESSAGE = new MessageOut<Object>(false, "无权修改他人信息");

	// 添加成功/失败 提示信息
	public static final MessageOut<Object> ADD_OK_MESSAGE = new MessageOut<Object>(true, "新增成功");
	public static final MessageOut<Object> ADD_FAIL_MESSAGE = new MessageOut<Object>(false, "新增失败,请刷新再试");
	// public static final String ADD_OK = "新增成功";
	// public static final String ADD_FAIL = "新增失败,请刷新再试";

	// 修改成功/失败 提示信息
	public static final MessageOut<Object> UPDATE_OK_MESSAGE = new MessageOut<Object>(true, "修改成功");
	public static final MessageOut<Object> UPDATE_FAIL_MESSAGE = new MessageOut<Object>(false, "修改失败,请刷新再试");
	// 修改成功/失败 提示信息
	// public static final String UPDATE_OK = "修改成功";
	// public static final String UPDATE_FAIL = "修改失败,请刷新再试";

	// 删除成功/失败
	public static final MessageOut<Object> DELETE_OK_MESSAGE = new MessageOut<Object>(true, "删除成功");
	public static final MessageOut<Object> DELETE_FAIL_MESSAGE = new MessageOut<Object>(false, "删除失败,请刷新再试");
	// 删除成功/失败
	// public static final String DELETE_OK = "删除成功";
	// public static final String DELETE_FAIL = "删除失败,请刷新再试";

	public List<T> getRows() {
		return null;
	}

	public int getTotal() {
		return 0;
	}

}
