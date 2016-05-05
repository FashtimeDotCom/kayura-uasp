/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.po;

import java.util.Date;

import org.kayura.utils.StringUtils;

/**
 * Department
 *
 * @author liangxia@live.com
 */
public class Department {

	public static final Integer STATUS_DISABLED = 0;
	public static final Integer STATUS_ENABLED = 1;

	private String departmentId;
	private String parentId;
	private String parentName;
	private String companyId;
	private String companyName;
	private String tenantId;
	private String code;
	private String name;
	private String description;
	private Integer serial;
	private Integer status;
	private Date updatedTime;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getParentId() {
		return !StringUtils.isEmpty(parentId) ? this.parentId : null;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getCompanyId() {
		return !StringUtils.isEmpty(companyId) ? this.companyId : null;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTenantId() {
		return !StringUtils.isEmpty(tenantId) ? this.tenantId : null;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}
