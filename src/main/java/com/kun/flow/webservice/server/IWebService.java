/**
 * Program  : IWebService.java
 * Author   : songkun
 * Create   : 2014年4月19日 上午11:59:33
 *
 */

package com.kun.flow.webservice.server;

import javax.jws.WebService;

/**
 * web service 接口
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月19日 上午11:59:33
 */
@WebService
public interface IWebService {

	/**
	 * 根据用户名/加密密码登录，成功true,失败false
	 * 
	 * @author songkun
	 * @create 2014年4月19日 下午12:06:12
	 * @since
	 * @param userName
	 * @param md5Pswd
	 * @return true 成功, false失败
	 * 
	 */
	public boolean login(String userName, String md5Pswd);

	/**
	 * 测试用的
	 * 
	 * @author songkun
	 * @create 2014年4月19日 下午2:40:30
	 * @since
	 * @param name
	 * @return
	 */
	public String sayHello(String name);
}
