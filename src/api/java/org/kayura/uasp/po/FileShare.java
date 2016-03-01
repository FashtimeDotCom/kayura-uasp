/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.po;

import java.util.Date;

/**
 * FileShare
 *
 * @author liangxia@live.com
 */
public class FileShare {

	private String fileShareId;
	private String sharerId;
	private String sharerName;
	private String receiverId;
	private String folderId;
	private String folderName;
	private String frId;
	private Date createTime;

	public String getFileShareId() {
		return fileShareId;
	}

	public void setFileShareId(String fileShareId) {
		this.fileShareId = fileShareId;
	}

	public String getSharerId() {
		return sharerId;
	}

	public void setSharerId(String sharerId) {
		this.sharerId = sharerId;
	}

	public String getSharerName() {
		return sharerName;
	}

	public void setSharerName(String sharerName) {
		this.sharerName = sharerName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFrId() {
		return frId;
	}

	public void setFrId(String frId) {
		this.frId = frId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
