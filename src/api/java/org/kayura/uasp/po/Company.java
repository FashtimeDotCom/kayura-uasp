/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.po;

import java.util.Date;

/**
 * 组织机构 公司实体类型.
 * 
 * @author liangxia@live.com
 */
public class Company {

	public static final Integer ROWTYPE_CATEOGRY = 0;
	public static final Integer ROWTYPE_COMPANY = 1;

	public static final Integer STATUS_DISABLED = 0;
	public static final Integer STATUS_ENABLED = 1;

	private String companyId;
	private String tenantId;
	private String parentId;
	private Integer rowType;
	private String code;
	private String shortName;
	private String fullName;
	private String description;
	private DictItem tndustry;
	private String address;
	private String postcode;
	private String telephone;
	private String email;
	private String fax;
	private String linkman;
	private Date estaDate;
	private Integer Serial;
	private Integer status;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DictItem getTndustry() {
		return tndustry;
	}

	public void setTndustry(DictItem tndustry) {
		this.tndustry = tndustry;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public Date getEstaDate() {
		return estaDate;
	}

	public void setEstaDate(Date estaDate) {
		this.estaDate = estaDate;
	}

	public Integer getSerial() {
		return Serial;
	}

	public void setSerial(Integer serial) {
		Serial = serial;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
