/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.po;

/**
 * 组织机构项.
 *
 * @author liangxia@live.com
 */
public class OrganizItem {

	public static final Integer ORGTYPE_COMPANY = 1;
	public static final Integer ORGTYPE_DEPART = 2;
	public static final Integer ORGTYPE_POSITION = 3;

	private String orgId;
	private String parentId;
	private String code;
	private String displayName;
	private Integer orgType;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

}
