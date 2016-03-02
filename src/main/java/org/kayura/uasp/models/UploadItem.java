/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.models;

import java.io.Serializable;

/**
 * @author liangxia@live.com
 */
public class UploadItem implements Serializable {

	private static final long serialVersionUID = 5013405337626162388L;

	private String bizId;
	private String folderId;
	private String category;
	private Integer serial;
	private Boolean allowChange;
	private Boolean isEncrypt;
	private String tags;

	public UploadItem() {
		this.allowChange = false;
		this.isEncrypt = false;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
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

	public Boolean getAllowChange() {
		return allowChange;
	}

	public void setAllowChange(Boolean allowChange) {
		this.allowChange = allowChange;
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
