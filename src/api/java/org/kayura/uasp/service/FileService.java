/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service;

import java.util.List;

import org.kayura.type.GeneralResult;
import org.kayura.type.Result;
import org.kayura.uasp.vo.FileDownload;
import org.kayura.uasp.po.FileFolder;
import org.kayura.uasp.po.Group;
import org.kayura.uasp.vo.FileContentUpdate;
import org.kayura.uasp.vo.FileUpload;
import org.kayura.uasp.vo.FileUploadResult;

/**
 * @author liangxia@live.com
 */
public interface FileService {

	/**
	 * 文件上传.
	 * 
	 * @param fileUpload 上传的文件信息.
	 * @return 在返回 frid(String), fileid(String), newfile(Boolean).
	 */
	Result<FileUploadResult> upload(FileUpload fileUpload);

	/**
	 * 通过文件关系ID，下载该文件信息或内容.
	 * 
	 * @param frId 文件关系Id.
	 * @return 返回文件下载数据.
	 */
	Result<FileDownload> download(String frId);

	/**
	 * 更新文件内容信息.
	 * 
	 * @param fileUpdate 更新后的文件信息.
	 * @return 返回更新结果.
	 */
	GeneralResult updateContent(FileContentUpdate fileUpdate);

	/**
	 * 查找符合条件的目录信息.
	 * 
	 * @param userId 该用户下可用的目录.
	 * @return 返回该租户下可用的目录.
	 */
	Result<List<FileFolder>> findFolders(String userId);

}
