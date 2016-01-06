/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.uasp.web.UploadModel;
import org.kayura.web.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件与图片的上传下载控制器类.
 * 
 * @author liangxia@live.com
 */
@Controller
public class FileController extends BaseController {

	public FileController() {
		this.setViewRootPath("views/");
	}

	/**
	 * 文件上传请求地址.
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile[] files, HttpServletRequest req,
			HttpServletResponse res, Model model, UploadModel um) {

		String fileNames = "";
		for (MultipartFile file : files) {
			fileNames += "File " + file.getOriginalFilename() + "<br>";
		}
		model.addAttribute("message", fileNames + " uploaded successfully");

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
