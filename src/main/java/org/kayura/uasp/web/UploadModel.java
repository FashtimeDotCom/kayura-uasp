/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.web;

import java.io.Serializable;

/**
 * @author liangxia@live.com
 */
public class UploadModel implements Serializable {

	private static final long serialVersionUID = 5013405337626162388L;
	
	private String bizId;
	private String category;
	private Integer serial;
	private Boolean isChange;
	private Boolean isEncrypt;
	private String tags;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public Boolean getIsChange() {
		return isChange;
	}

	public void setIsChange(Boolean isChange) {
		this.isChange = isChange;
	}

	public Boolean getIsEncrypt() {
		return isEncrypt;
	}

	public void setIsEncrypt(Boolean isEncrypt) {
		this.isEncrypt = isEncrypt;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
