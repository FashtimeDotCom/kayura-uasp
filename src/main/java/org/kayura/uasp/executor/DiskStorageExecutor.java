/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.executor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kayura.exceptions.KayuraException;
import org.kayura.utils.DateUtils;

/**
 * @author liangxia@live.com
 */
public class DiskStorageExecutor implements StorageExecutor {

	private static final Log logger = LogFactory.getLog(DiskStorageExecutor.class);

	private Map<String, String> uploadPaths;
	private Long minSpace;

	public DiskStorageExecutor() {
		this.uploadPaths = new HashMap<String, String>();
	}

	private String getUsableDir() {

		for (String key : uploadPaths.keySet()) {

			String dir = uploadPaths.get(key);

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

		throw new KayuraException("上传文件库的存储空间不足。");
	}

	public void setMinSpace(Long minSpace) {
		this.minSpace = minSpace;
	}

	public void setUploadPaths(Map<String, String> uploadPaths) {
		this.uploadPaths = uploadPaths;
	}

	private String convertDiskPath(String logicPath) {

		String diskPath = logicPath;
		for (String key : uploadPaths.keySet()) {

			if (logicPath.startsWith(key)) {
				diskPath = logicPath.replace(key, uploadPaths.get(key));
				break;
			}
		}
		return diskPath;
	}

	@Override
	public void write(String fileName, String logicPath, byte[] fileContent) {

		FileOutputStream out = null;
		try {

			String diskPath = convertDiskPath(logicPath);
			Path dirPath = Paths.get(diskPath);

			File filePath = dirPath.toFile();
			if (!filePath.exists()) {
				filePath.mkdirs();
			}

			File file = new File(dirPath.toString(), fileName);

			if (!file.exists()) {
				file.createNewFile();

				out = new FileOutputStream(file);
				out.write(fileContent);
				out.flush();
				out.close();
			}

		} catch (Exception e) {
			logger.error("将上传文件保存至磁盘时发生异常。", e);
			throw new KayuraException(e);
		}
	}

	@Override
	public String getLogicPath() {

		String dirKey = getUsableDir();

		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String subPath = format.format(DateUtils.now());

		return dirKey + subPath + "\\";
	}

	@Override
	public byte[] read(String fileName, String logicPath) {

		String diskPath = convertDiskPath(logicPath);
		File file = new File(diskPath.toString(), fileName);
		try {

			if (file.exists()) {
				
				Long fileLen = file.length();
				byte[] fileContent = new byte[fileLen.intValue()];

				FileInputStream is = new FileInputStream(file);
				is.read(fileContent);
				is.close();

				return fileContent;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return new byte[0];
	}

}
