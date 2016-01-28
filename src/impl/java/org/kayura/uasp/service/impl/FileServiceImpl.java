/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.kayura.type.GeneralResult;
import org.kayura.type.Result;
import org.kayura.uasp.dao.FileMapper;
import org.kayura.uasp.po.FileInfo;
import org.kayura.uasp.po.FileRelation;
import org.kayura.uasp.service.FileService;
import org.kayura.uasp.vo.FileDownload;
import org.kayura.uasp.vo.FileUpload;
import org.kayura.utils.DateUtils;
import org.kayura.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liangxia@live.com
 */
public class FileServiceImpl implements FileService {

	@Autowired
	private FileMapper fileMapper;

	@Override
	public GeneralResult upload(FileUpload fu) {

		FileRelation fr = new FileRelation();
		fr.setFrId(KeyUtils.newId());
		fr.setTenantId(fu.getTenantId());
		fr.setBizId(fu.getBizId());
		fr.setCategory(fu.getCategory());
		fr.setUploaderId(fu.getUploaderId());
		fr.setUploaderName(fu.getUploaderName());
		fr.setUploadTime(DateUtils.now());
		fr.setAllowChange(fu.getAllowChange());
		fr.setSerial(fu.getSerial());
		fr.setTags(fu.getTags());

		// 在不允许修改文件内容时,可引用相同文件,以减少磁盘存储.
		String fileId = null;
		Boolean isNewFile = false;
		if (!fu.getAllowChange()) {			
			fileId = fileMapper.getKeyForFileInfo(fu.getMd5());
		}

		// 若没有相同的文件内容,将创建新文件.
		if (fileId == null) {
			fileId = KeyUtils.newId();
			
			FileInfo fi = new FileInfo();
			fi.setFileId(fileId);
			fi.setContentType(fu.getContentType());
			fi.setFileSize(fu.getFileSize());
			fi.setPostfix(fu.getPostfix());
			fi.setDiskPath(fu.getDiskPath());
			fi.setMd5(fu.getMd5());
			fi.setIsEncrypted(fu.getIsEncrypt());
			fi.setSalt(fu.getSalt());
			fi.setStatus(FileInfo.STATUS_TEMP);
			
			// 将文件信息添加至数据库.
			fileMapper.insertFileInfo(fi);
			
			isNewFile = true;
		}
		
		// 记录文件信息Id, 将文件关联保存至数据库.
		fr.setFileId(fileId);
		fileMapper.insertFileRelation(fr);		

		// 创建返回值对象.
		GeneralResult r = new GeneralResult();
		r.addData("frid", fr.getFrId());
		r.addData("fileid", fr.getFileId());
		r.addData("newfile", isNewFile);
		
		return r;		
	}

	@Override
	public Result<FileDownload> download(String frId) {
		// TODO Auto-generated method stub
		return null;
	}

}
