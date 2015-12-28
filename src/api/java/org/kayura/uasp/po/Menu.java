/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.po;

import java.util.ArrayList;
import java.util.List;

import org.kayura.utils.StringUtils;

/**
 * @author liangxia@live.com
 */
public class Menu {

	public static final Integer TYPE_CATEGORY = 1;
	public static final Integer TYPE_ITEM = 2;

	private String menuId;
	private String parentId;
	private String menuPlanId;
	private Module module;
	private Integer type;
	private String displayName;
	private String description;
	private String icon;
	private Integer serial;
	private Boolean enabled;

	private List<Menu> subItems;

	public Menu() {
		setSubItems(new ArrayList<Menu>());
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuPlanId() {
		return menuPlanId;
	}

	public void setMenuPlanId(String menuPlanId) {
		this.menuPlanId = menuPlanId;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDisplayName() {

		return StringUtils.isEmpty(this.displayName) ? this.getModule().getName() : this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {

		return StringUtils.isEmpty(this.description) ? this.getModule().getDescription() : this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {

		return StringUtils.isEmpty(this.icon) ? this.getModule().getIcon() : this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public Boolean getEnabled() {

		return enabled && this.getModule().getEnabled();
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Menu> getSubItems() {
		return subItems;
	}

	public void setSubItems(List<Menu> subItems) {
		this.subItems = subItems;
	}

}
