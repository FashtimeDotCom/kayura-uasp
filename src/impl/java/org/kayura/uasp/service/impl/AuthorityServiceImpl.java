/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service.impl;

import java.util.List;
import java.util.Map;

import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.dao.AuthorityMapper;
import org.kayura.uasp.po.Group;
import org.kayura.uasp.po.MenuItem;
import org.kayura.uasp.po.MenuScheme;
import org.kayura.uasp.po.Module;
import org.kayura.uasp.po.Role;
import org.kayura.uasp.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author liangxia@live.com
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	public Result<List<MenuScheme>> getUserMenus(String userId) {

		List<MenuScheme> items = authorityMapper.getUserMenus(userId);
		return new Result<List<MenuScheme>>(items);
	}

	@Override
	public Result<PageList<MenuScheme>> findMenuSchemes(String tenantId, Map<String, Object> args,
			PageParams pageParams) {

		PageList<MenuScheme> items = authorityMapper.findMenuSchemes(tenantId, args, pageParams);
		return new Result<PageList<MenuScheme>>(items);
	}

	@Override
	public Result<MenuScheme> getMenuSchemeById(String menuSchemeId) {

		MenuScheme item = authorityMapper.getMenuSchemeById(menuSchemeId);
		return new Result<MenuScheme>(item);
	}

	@Override
	public GeneralResult createMenuScheme(MenuScheme menuScheme) {

		try {
			
			authorityMapper.createMenuScheme(menuScheme);
		} catch (Exception e) {
			return Result.error("创建时发现异常。", e);
		}

		return Result.succeed("创建完成。");
	}

	@Override
	public GeneralResult updateMenuScheme(MenuScheme menuScheme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteMenuScheme(String menuSchemeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteMenuSchemes(List<String> menuSchemeIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<PageList<MenuItem>> findMenuItems(String tenantId, String parentId, Map<String, Object> args,
			PageParams pageParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<MenuItem> getMenuItemById(String menuItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult createMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult updateMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteMenuItem(String menuItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteMenuItems(List<String> menuItemIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<PageList<Module>> findModules(String parentId, Map<String, Object> args, PageParams pageParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Module> getModuleById(String moduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult createModule(Module module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult updateModule(Module module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteModule(String moduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteModules(List<String> moduleIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<PageList<Role>> findRoles(String tenantId, Map<String, Object> args, PageParams pageParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Role> getRoleById(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult createRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult updateRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteRole(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteRoles(List<String> roleIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult addRoleModule(String roleId, String moduleId, List<String> actions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult removeRoleModule(String roleId, String moduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<PageList<Group>> findGroups(String tenantId, Map<String, Object> args, PageParams pageParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Group> getGroupById(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult createGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult updateGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteGroup(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteGroups(List<String> groupIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult addGroupRole(String groupId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult removeGroupRole(String groupId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult addUserGroup(String userId, String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult removeUserGroup(String userId, String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult addUserRole(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult removeUserRole(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<Role>> findUserRoles(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<Group>> findUserGroups(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
