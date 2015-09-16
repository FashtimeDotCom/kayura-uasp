/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.services;

/**
 * @author liangxia@live.com
 */
public interface UserService {

	/**
	 * 验证用户名及密码是否正确.
	 * 
	 * @param userName 登录用户名.
	 * @param password 用户输入的密码.
	 * @return 返回是否验证通过.
	 */
	boolean verifyUser(String userName, String password);

}
