/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.po;

/**
 * Department
 *
 * @author liangxia@live.com
 */
public class Department {

	public static final Integer ROWTYPE_CATEOGRY = 0;
	public static final Integer ROWTYPE_COMPANY = 1;

	public static final Integer STATUS_DISABLED = 0;
	public static final Integer STATUS_ENABLED = 1;

	private String departmentId;
	private String parentId;
	private Company company;
	private Integer rowType;
	private String code;
	private String name;
	private String description;
	private Integer serial;
	private Integer status;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getRowType() {
		return rowType;
	}

	public void setRowType(Integer rowType) {
		this.rowType = rowType;
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

}
