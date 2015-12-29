/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.dao;

import java.util.List;
import java.util.Map;

import org.kayura.core.BaseDao;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.po.Group;
import org.kayura.uasp.po.MenuItem;
import org.kayura.uasp.po.MenuScheme;
import org.kayura.uasp.po.Module;
import org.kayura.uasp.po.Role;

/**
 * @author liangxia@live.com
 */
public interface AuthorityMapper extends BaseDao {

	// 权限交互接口.

	/**
	 * 获取用户拥有权限的菜单方案及菜单项.
	 * 
	 * @param userId
	 * @return
	 */
	List<MenuScheme> getUserMenus(String userId);

	// 菜单方案（MenuScheme）.

	/**
	 * 获取菜单方案维护列表.
	 * 
	 * @param tenantId 所属租户Id.
	 * @param args 查询参数. keyword.
	 * @param pageParams 分页参数.
	 * @return 返回分页的菜单方案集合.
	 */
	PageList<MenuScheme> findMenuSchemes(String tenantId, Map<String, Object> args, PageParams pageParams);

	MenuScheme getMenuSchemeById(String menuSchemeId);

	void createMenuScheme(MenuScheme menuScheme);

	void updateMenuScheme(MenuScheme menuScheme);

	void deleteMenuScheme(String menuSchemeId);

	void deleteMenuSchemes(List<String> menuSchemeIds);

	// 菜单项（MenuItem）.

	Result<PageList<MenuItem>> findMenuItems(String tenantId, String parentId, Map<String, Object> args,
			PageParams pageParams);

	Result<MenuItem> getMenuItemById(String menuItemId);

	void createMenuItem(MenuItem menuItem);

	void updateMenuItem(MenuItem menuItem);

	void deleteMenuItem(String menuItemId);

	void deleteMenuItems(List<String> menuItemIds);

	// 功能模块（Module）.

	Result<PageList<Module>> findModules(String parentId, Map<String, Object> args, PageParams pageParams);

	Result<Module> getModuleById(String moduleId);

	void createModule(Module module);

	void updateModule(Module module);

	void deleteModule(String moduleId);

	void deleteModules(List<String> moduleIds);

	// 角色（Role）.

	Result<PageList<Role>> findRoles(String tenantId, Map<String, Object> args, PageParams pageParams);

	Result<Role> getRoleById(String roleId);

	void createRole(Role role);

	void updateRole(Role role);

	void deleteRole(String roleId);

	void deleteRoles(List<String> roleIds);

	// 角色模块（RoleModule）.

	void addRoleModule(String roleId, String moduleId, List<String> actions);

	void removeRoleModule(String roleId, String moduleId);

	// 组（Group）.

	Result<PageList<Group>> findGroups(String tenantId, Map<String, Object> args, PageParams pageParams);

	Result<Group> getGroupById(String groupId);

	void createGroup(Group group);

	void updateGroup(Group group);

	void deleteGroup(String groupId);

	void deleteGroups(List<String> groupIds);

	// 组角色（GroupRole）.

	void addGroupRole(String groupId, String roleId);

	void removeGroupRole(String groupId, String roleId);

	// 用户组（UserGroup）

	void addUserGroup(String userId, String groupId);

	void removeUserGroup(String userId, String groupId);

	// 用户角色（UserRole）

	void addUserRole(String userId, String roleId);

	void removeUserRole(String userId, String roleId);

	// 其它.

	Result<List<Role>> findUserRoles(String userId);

	Result<List<Group>> findUserGroups(String userId);
	
}
