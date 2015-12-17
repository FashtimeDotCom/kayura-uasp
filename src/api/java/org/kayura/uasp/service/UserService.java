/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service;

import java.util.Date;

import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.uasp.vo.AutoLoginVo;
import org.kayura.uasp.vo.UserVo;

/**
 * UserService
 * 
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
	PageList<UserVo> findUsers(String keyword, Integer[] status, PageParams pageParams);

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
	GeneralResult saveOrUpdateUser(UserVo user);

	/**
	 * 删除一个用户账号.
	 * 
	 * @param userId 用户账号Id.
	 */
	void deleteUser(String userId);

	/**
	 * @param loginName 有2种值类型：
	 * 		{租户Id}#{用户名}，如： YFS43TF#user1
	 * 		{用户名}，如：user1
	 * @return
	 */
	UserVo getUserByUserName(String loginName);

	/**
	 * @param userId
	 */
	UserVo getUserById(String userId);
	
	/**
	 * 创建一个登录 Token 记录.
	 * 
	 * @param rememberMe
	 */
	void createLoginToken(AutoLoginVo rememberMe);

	
	/**
	 * 更新一个登录 Token 与 lastUsed 值.
	 * @param seriesId
	 * @param token
	 * @param lastUsed
	 */
	void updateLoginToken(String seriesId, String token, Date lastUsed);
	
	/**
	 * 获取登录 Token 数据.
	 * 
	 * @param seriesId
	 * @return
	 */
	AutoLoginVo getLoginTokenById(String seriesId);
	
	/**
	 * 删除指定用户下的所有登录信息.
	 * 
	 * @param userId 用户Id.
	 */
	void removeLoginTokensByUser(String userId);
}
