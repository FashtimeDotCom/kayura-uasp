/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.auth.dao;

import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.uasp.auth.po.User;
import org.springframework.stereotype.Repository;

/**
 * @author liangxia@live.com
 */
@Repository
public interface UserMapper {
	
	PageList<User> findUsersByMap(Map<String, Object> args, PageBounds pageBounds);
	
	/**
	 * 检查用户记录是否存在.
	 * 
	 * @param args 可选参数:
	 * <p> userId 用户Id
	 * <p> userName 登录的用户名.
	 * <p> password 密码字符串.
	 * @return 如果存在返回 true 否则返回 false.
	 */
	Boolean exists(Map<String, Object> args);

	void insertUser(User user);
	
	void updateUser(User user);

	void deleteUser(String userId);

	User getUserById(String userId);
}
