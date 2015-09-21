/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.auth.service;

import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.uasp.auth.po.User;

/**
 * @author liangxia@live.com
 */
public interface UserService {

	/**
	 * 查询符合条件的用户账号信息.
	 * 
	 * @param keyword 查询关键字.
	 * @param status 用户账号的状态.
	 * @param pageParams 分页信息.
	 * @return 返回已经分页的集合及总记录信息.
	 */
	PageList<User> findUsers(String keyword, Integer[] status, PageParams pageParams);

	/**
	 * 验证用户名及密码是否正确.
	 * 
	 * @param userName 登录用户名.
	 * @param password 用户输入的密码.
	 * @return 返回是否验证通过.
	 */
	boolean verifyUser(String userName, String password);
	
	/**
	 * 保存或者更新一个用户账号.
	 * 
	 * @param user 用户账号实例对象.
	 */
	void saveOrUpdateUser(User user);
	
	/**
	 * 删除一个用户账号.
	 * 
	 * @param userId 用户账号Id.
	 */
	void deleteUser(String userId);

	/**
	 * @param userId
	 * @return
	 */
	User getUserById(String userId);
	
}
