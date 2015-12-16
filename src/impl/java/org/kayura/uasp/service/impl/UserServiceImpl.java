/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.GeneralResult;
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

		if (status != null) {
			args.put("status", StringUtils.join(",", status));
		}

		PageList<User> list = userMapper.findUsersByMap(args, new PageBounds(pageParams));
		return UserConvert.toVos(list);
	}

	@Override
	public UserVo getUserByUserName(String loginName) {

		Map<String, Object> args = new HashMap<String, Object>();

		boolean isTenant = loginName.contains("#");
		if (isTenant) {
			
			String[] values = loginName.split("#");
			
			String tenantId = values[0];
			String userName = values[1];

			args.put("tenantId", tenantId);
			args.put("userName", userName);
		} else {
			
			args.put("userName", loginName);
		}

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
	public GeneralResult saveOrUpdateUser(UserVo user) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("userId", user.getUserId());

		GeneralResult result = new GeneralResult("保存成功.");
		try {
			User entity = UserConvert.toEntity(user);
			if (userMapper.isExistsByMap(args)) {
				userMapper.updateUser(entity);
			} else {
				userMapper.insertUser(entity);
			}
		} catch (Exception e) {
			result.setCode(-1);
			result.setMessage("保存失败。原因：" + e.getMessage());
		}

		return result;
	}

	@Override
	public void deleteUser(String userId) {
		userMapper.deleteUser(userId);
	}

	@Override
	public UserVo getUserById(String userId) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("userId", userId);

		User user = userMapper.getUserByMap(args);
		return UserConvert.toVo(user);
	}
}
