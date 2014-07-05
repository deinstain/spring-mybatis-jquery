/**
 * Program  : Client.java
 * Author   : songkun
 * Create   : 2014年4月19日 下午4:53:40
 *
 */

package com.kun.flow.webservice.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kun.flow.webservice.server.IWebService;

/**
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月19日 下午4:53:40
 */
public class Client {

	public static void main(String[] args) {
		// START SNIPPET: client
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"webservice_client.xml"});

		IWebService client = (IWebService) context.getBean("client");

		String response = client.sayHello("Joe");
		System.out.println("Response: " + response);
		if (client.login("test", "123456")) {
			System.out.println("you are right !");
		} else {
			System.out.println("you are wrong !");
		}
		System.exit(0);
		// END SNIPPET: client
	}

	public static void noXmlInvoke() {
		// JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
		// bean.getInInterceptors().add(new LoggingInInterceptor());
		// bean.getInFaultInterceptors().add(new LoggingOutInterceptor());
		// bean.setServiceClass(IWebService.class);
		// bean.setAddress("http://localhost:8080/flow/services");
		// IWebService helloWorldService = (IWebService) bean.create();
		// String result = helloWorldService.sayHello("Kevin");
		// System.out.println(result);
		// JaxWsDynamicClientFactory clientFactory =
		// JaxWsDynamicClientFactory.newInstance();
		// org.apache.cxf.endpoint.Client client = clientFactory
		// .createClient("http://localhost:8080/flow/services/WebService?wsdl");
		// try {
		// Object[] result = client.invoke("sayHello", "KEVIN");
		// System.out.println(result[0]);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
	public static void invoke() {
		// 创建WebService客户端代理工厂
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		// 注册WebService接口
		factory.setServiceClass(IWebService.class);
		// 设置WebService地址
		factory.setAddress("http://localhost:8080/flow/services/webService");
		IWebService service = (IWebService) factory.create();
		System.out.println("invoke helloServices webservice...");
		String hello = service.sayHello("vicky");

		System.out.println(hello);
	}
}
