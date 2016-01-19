/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.executor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
import org.kayura.uasp.web.UploadModel;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public GeneralResult storage(UploadModel model, MultipartFile uploadedFile) {

		GeneralResult result = Result.succeed();

		String dirKey = getUsableDir();
		String diskDir = uploadPaths.get(dirKey);

		result.addData("dirKey", dirKey);

		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String subPath = format.format(now);

		Path filePath = Paths.get(diskDir, subPath);
		result.addData("dirPath", filePath.toString());

		FileOutputStream out = null;
		try {

			File path = filePath.toFile();
			if (!path.exists()) {
				path.mkdirs();
			}

			File file = new File(filePath.toString(), model.getFileId());
			result.addData("filePath", file.getAbsolutePath());

			if (!file.exists()) {
				file.createNewFile();

				out = new FileOutputStream(file);
				out.write(uploadedFile.getBytes());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			String msg = "将上传文件保存至磁盘时发生异常。" ;
			logger.error(msg, e);
			return Result.error(msg, e);
		}

		return result;
	}

	public void setMinSpace(Long minSpace) {
		this.minSpace = minSpace;
	}

	public void setUploadPaths(Map<String, String> uploadPaths) {
		this.uploadPaths = uploadPaths;
	}

}
