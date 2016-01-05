/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kayura.web.BaseController;
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
		this.setViewRootPath("views/file/");
	}

	@RequestMapping(value = "/file/upload", method = RequestMethod.GET)
	public String fileUpload() {

		return this.viewResult("upload");
	}

	/**
	 * 文件上传请求地址.
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile[] files, Model model, HttpServletRequest req,
			HttpServletResponse res) {

		String fileNames = "";
		for (MultipartFile file : files) {
			fileNames += "File " + file.getOriginalFilename() + "<br>";
		}
		model.addAttribute("message", fileNames + " uploaded successfully");

		return this.viewResult("upload");
	}

	/**
	 * 文件下载请求地址.
	 */
	@RequestMapping(value = "/file/get", method = RequestMethod.GET)
	public void getFile(HttpServletRequest req, HttpServletResponse res) {

	}

	/**
	 * 图片上传请求地址.
	 */
	@RequestMapping(value = "/img/upload", method = RequestMethod.POST)
	public void imgUpload(MultipartFile file, Model model, HttpServletRequest req, HttpServletResponse res) {

		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
	}

	/**
	 * 图片下载请求地址.
	 */
	@RequestMapping(value = "/img/get", method = RequestMethod.GET)
	public void getImage(HttpServletRequest req, HttpServletResponse res) {

	}
}
