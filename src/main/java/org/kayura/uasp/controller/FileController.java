/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.type.GeneralResult;
import org.kayura.uasp.executor.StorageExecutor;
import org.kayura.uasp.web.UploadModel;
import org.kayura.web.BaseController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件与图片的上传下载控制器类.
 * 
 * @author liangxia@live.com
 */
@Controller
public class FileController extends BaseController {

	@Autowired
	private StorageExecutor storageExecutor;

	public FileController() {
		this.setViewRootPath("views/");
	}

/*	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String maxUploadSizeException(Exception ex, Map<String, Object> map) {

		execute(map, new PostAction() {

			@Override
			public void invoke(PostResult r) {
				r.setError("上传的文件超过大小.", ex);
			}
		});

		return this.viewResult("file/error");
	}*/

	@RequestMapping(value = "/file/upload", method = RequestMethod.GET)
	public String fileUpload() {

		return this.viewResult("file/upload");
	}

	/**
	 * 文件上传请求地址.
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile[] files, HttpServletRequest req,
			HttpServletResponse res, Map<String, Object> map, UploadModel um) {

		execute(map, new PostAction() {

			@Override
			public void invoke(PostResult r) {

				List<String> succeedFiles = new ArrayList<String>();
				List<String> errorFiles = new ArrayList<String>();

				for (MultipartFile file : files) {

					if (file.getSize() > 0) {

						String fileName = file.getOriginalFilename();
						GeneralResult result = null;

						try {
							result = storageExecutor.storage(fileName, file.getBytes());
							if (result.isSucceed()) {
								succeedFiles.add(fileName);
							} else {
								errorFiles.add(fileName);
							}
						} catch (IOException e) {
							r.setException(e);
							errorFiles.add(fileName);
						}
					}
				}

				if (errorFiles.size() > 0) {
					r.setSuccess("文件部分上传成功。");
				} else {
					r.setSuccess("文件全部上传成功。");
				}

				r.addAttr("succeedFiles", succeedFiles);
				r.addAttr("errorFiles", errorFiles);
			}
		});

		return this.viewResult("file/upload");
	}

	/**
	 * 文件下载请求地址.
	 */
	@RequestMapping(value = "/file/get", method = RequestMethod.GET)
	public void getFile(HttpServletRequest req, HttpServletResponse res) {

	}

	@RequestMapping(value = "/file/list", method = RequestMethod.GET)
	public String fileList(HttpServletRequest req, HttpServletResponse res) {

		return this.viewResult("file/list");
	}

}
