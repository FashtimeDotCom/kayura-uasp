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

	List<MenuScheme> getUserMenus(String userId);

	// 菜单方案（MenuScheme）.

	PageList<MenuScheme> findMenuSchemes(Map<String, Object> args, PageParams pageParams);

	MenuScheme getMenuSchemeByMap(Map<String, Object> args);

	void createMenuScheme(MenuScheme menuScheme);

	void updateMenuScheme(MenuScheme menuScheme);

	void deleteMenuSchemes(List<String> menuSchemeIds);

	// 菜单项（MenuItem）.

	PageList<MenuItem> findMenuItems(Map<String, Object> args, PageParams pageParams);

	MenuItem getMenuItemByMap(Map<String, Object> args);

	void createMenuItem(MenuItem menuItem);

	void updateMenuItem(MenuItem menuItem);

	void deleteMenuItem(String menuItemId);

	void deleteMenuItems(List<String> menuItemIds);

	// 功能模块（Module）.

	PageList<Module> findModules(Map<String, Object> args, PageParams pageParams);

	Module getModuleById(String moduleId);

	void createModule(Module module);

	void updateModule(Module module);

	void deleteModule(String moduleId);

	void deleteModules(List<String> moduleIds);

	// 角色（Role）.

	PageList<Role> findRoles(Map<String, Object> args, PageParams pageParams);

	Role getRoleById(String roleId);

	void createRole(Role role);

	void updateRole(Role role);

	void deleteRole(String roleId);

	void deleteRoles(List<String> roleIds);

	// 角色模块（RoleModule）.

	void addRoleModule(String roleId, String moduleId, String actions);

	void removeRoleModule(String roleId, String moduleId);

	// 组（Group）.

	PageList<Group> findGroups(Map<String, Object> args, PageParams pageParams);

	Group getGroupById(String groupId);

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

	List<Role> findUserRoles(String userId);

	List<Group> findUserGroups(String userId);

}
