package com.kun.flow.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kun.flow.constants.Constants;
import com.kun.flow.exception.ServiceException;
import com.kun.flow.model.Operater;
import com.kun.flow.service.IOperaterService;
import com.kun.flow.web.response.MessageOut;
import com.kun.flow.web.response.Out;

@Controller
@RequestMapping("/login")
public class LoginControl extends BaseControl {

	public IOperaterService getOperaterService() {
		return (IOperaterService) this.getService();
	}

	/**
	 * 登录
	 * 
	 * @author songkun
	 * @return String
	 * @date 2011-6-1 下午12:48:45
	 * @since 2.0.0
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Out login(Operater operater) {
		try {
			Operater tmp = this.getOperaterService().validate(operater);
			if (tmp == null) {// 登陆失败
				return MessageOut.LOGIN_FAIL_MESSAGE;
			}
			((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(true)
					.setAttribute(Constants.USER_INFO, tmp);
			return MessageOut.LOGIN_OK_MESSAGE;
		} catch (ServiceException e) {
			this.getLogger().error(e);
		} catch (Exception e) {
			this.getLogger().error(e);
		}
		return MessageOut.LOGIN_FAIL_MESSAGE;
	}

	/**
	 * 退出
	 * 
	 * @author songkun
	 * @return String
	 * @date 2011-6-1 下午12:49:55
	 * @since 2.0.0
	 */
	@RequestMapping("/logout.do")
	@ResponseBody
	public Out logout() {
		try {
			((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession()
					.removeAttribute(Constants.USER_INFO);
		} catch (Exception e) {
			this.getLogger().error(e);
		}
		return MessageOut.LOGOUT_OK_MESSAGE;
	}
}
