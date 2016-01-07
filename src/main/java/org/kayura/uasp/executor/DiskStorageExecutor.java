/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.executor;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kayura.exceptions.KayuraException;
import org.kayura.type.GeneralResult;
import org.kayura.type.Result;

/**
 * @author liangxia@live.com
 */
public class DiskStorageExecutor implements StorageExecutor {

	private static final Log logger = LogFactory.getLog(DiskStorageExecutor.class);

	private Map<String, String> diskDirs;
	private Long minSpace;

	public DiskStorageExecutor() {
		setDiskDirs(new HashMap<String, String>());
	}

	private String getUsableDir() {

		for (String key : diskDirs.keySet()) {

			String dir = diskDirs.get(key);

			File f = new File(dir);

			if (!f.exists()) {
				if (!f.mkdirs()) {
					continue;
				}
			}

			Long fg = f.getFreeSpace();

			if (fg > minSpace) {
				return key;
			}
		}

		throw new KayuraException("文件存储配置的文件库空间不足。");
	}

	@Override
	public GeneralResult storage(String fileId, byte[] content) {

		GeneralResult result = Result.succeed();

		String dirKey = getUsableDir();
		String diskDir = diskDirs.get(dirKey);

		result.addAttr("dirKey", dirKey);

		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String subPath = format.format(now);

		Path filePath = Paths.get(diskDir, subPath);
		result.addAttr("dirPath", filePath.toString());

		FileOutputStream out = null;
		try {

			File path = filePath.toFile();
			if (!path.exists()) {
				path.mkdirs();
			}

			File file = new File(filePath.toString(), fileId);
			result.addAttr("filePath", file.getAbsolutePath());

			if (!file.exists()) {
				file.createNewFile();
				out = new FileOutputStream(file);
				out.write(content);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			String msg = "将上传文件保存至磁盘时发生异常。";
			logger.error(msg, e);
			return Result.error(msg, e);
		}

		return result;
	}

	public void setMinSpace(Long minSpace) {
		this.minSpace = minSpace;
	}

	public void setDiskDirs(Map<String, String> diskDirs) {
		this.diskDirs = diskDirs;
	}

}
