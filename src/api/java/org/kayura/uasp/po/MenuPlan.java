/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.po;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangxia@live.com
 */
public class MenuPlan {

	private String menuPlanId;
	private String tenantId;
	private String code;
	private String name;

	private List<Menu> menus;

	public MenuPlan() {
		setMenus(new ArrayList<Menu>());
	}

	public String getMenuPlanId() {
		return menuPlanId;
	}

	public void setMenuPlanId(String menuPlanId) {
		this.menuPlanId = menuPlanId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
