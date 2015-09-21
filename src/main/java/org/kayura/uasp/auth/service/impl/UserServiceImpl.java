/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.auth.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.uasp.auth.dao.UserMapper;
import org.kayura.uasp.auth.po.User;
import org.kayura.uasp.auth.service.UserService;
import org.kayura.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liangxia@live.com
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public PageList<User> findUsers(String keyword, Integer[] status, PageParams pageParams) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("keyword", keyword);
		args.put("status", StringUtils.join(",", status));

		return userMapper.findUsersByMap(args, new PageBounds(pageParams));
	}

	@Override
	public User getUserById(String userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	public boolean verifyUser(String userName, String password) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("userName", userName);
		args.put("password", password);

		return userMapper.exists(args);
	}

	@Override
	public void saveOrUpdateUser(User user) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("userId", user.getUserId());

		if (userMapper.exists(args)) {
			userMapper.updateUser(user);
		} else {
			userMapper.insertUser(user);
		}
	}

	@Override
	public void deleteUser(String userId) {
		userMapper.deleteUser(userId);
	}
}
