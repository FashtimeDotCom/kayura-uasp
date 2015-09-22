/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.uasp.dao.UserMapper;
import org.kayura.uasp.po.User;
import org.kayura.uasp.service.UserService;
import org.kayura.uasp.vo.UserVo;
import org.kayura.uasp.vo.convert.UserConvert;
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
	public PageList<UserVo> findUsers(String keyword, Integer[] status, PageParams pageParams) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("keyword", keyword);
		args.put("status", StringUtils.join(",", status));

		PageList<User> list = userMapper.findUsersByMap(args, new PageBounds(pageParams));
		return UserConvert.toVos(list);
	}

	@Override
	public UserVo getUserByUserName(String userName) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("userName", userName);

		User user = userMapper.getUserByMap(args);
		return UserConvert.toVo(user);
	}

	@Override
	public boolean verifyUser(String userName, String password) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("userName", userName);
		args.put("password", password);

		return userMapper.isExistsByMap(args);
	}

	@Override
	public void saveOrUpdateUser(UserVo user) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("userId", user.getUserId());

		User entity = UserConvert.toEntity(user);
		if (userMapper.isExistsByMap(args)) {
			userMapper.updateUser(entity);
		} else {
			userMapper.insertUser(entity);
		}
	}

	@Override
	public void deleteUser(String userId) {
		userMapper.deleteUser(userId);
	}
}
