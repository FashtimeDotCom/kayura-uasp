/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.dao;

import java.util.List;
import java.util.Map;

import org.kayura.core.BaseDao;
import org.kayura.uasp.po.FileInfo;
import org.kayura.uasp.po.FileRelation;

/**
 * @author liangxia@live.com
 */
public interface FileMapper extends BaseDao {

	// FileInfo
	
	/**
	 * 保存一条文件信息记录至数据库.
	 * 
	 * @param fileInfo {@link FileInfo } 实例对象.
	 */
	void insertFileInfo(FileInfo fileInfo);
	
	/**
	 * 获取一个符合条件的文件信息记录.
	 * 
	 * @param args 支持条件有: fileId, md5.
	 * @return 返回 {@link FileInfo }
	 */
	FileInfo selectFileInfoByMap(Map<String, Object> args);

	/**
	 * 检查指定条件下的文件信息是否存在.
	 * 
	 * @param args 支持条件有: fileId, md5.
	 * @return 若存在返回 true 否则返回 false.
	 */
	Boolean fileInfoExistsByMap(Map<String, Object> args);

	// FileRelation
	
	/**
	 * 保存一个文件关系记录.
	 * 
	 * @param fileRelation {@link FileRelation} 实例对象.
	 */
	void insertFileRelation(FileRelation fileRelation);
	
	/**
	 * 获取单条文件关系记录.
	 * 
	 * @param args 
	 * @return
	 */
	FileRelation selectFileRelationByMap(Map<String, Object> args);
	
}
