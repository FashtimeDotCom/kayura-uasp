/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.services.impl;

import org.kayura.uasp.dao.UserMapper;
import org.kayura.uasp.services.UserService;
import org.springframework.stereotype.Service;

/**
 * @author liangxia@live.com
 */
@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	public boolean verifyUser(String userName, String password) {

		boolean isOk = userMapper.verifyUser(userName, password);
		return isOk;
	}

}
