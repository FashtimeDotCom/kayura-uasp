/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service;

import java.util.List;
import java.util.Map;

import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.uasp.po.MenuPlan;

/**
 * @author liangxia@live.com
 */
public interface AuthorityService {

	// 权限交互接口.

	/**
	 * 获取用户拥有权限的菜单方案及菜单项.
	 * 
	 * @param userId
	 * @return
	 */
	List<MenuPlan> getUserMenus(String userId);

	// 数据维护接口.

	/**
	 * 获取菜单方案维护列表.
	 * 
	 * @param tenantId 所属租户Id.
	 * @param args 查询参数. keyword
	 * @param pageParams 分页参数.
	 * @return 返回分页的菜单方案集合.
	 */
	PageList<MenuPlan> getMenuPlans(String tenantId, Map<String, Object> args, PageParams pageParams);
	
	
	
	
	
}
