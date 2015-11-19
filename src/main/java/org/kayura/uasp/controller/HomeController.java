/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kayura.web.BaseController;
import org.kayura.web.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liangxia@live.com
 */
@Controller
public class HomeController extends BaseController {

	@Value("#{sysProperties['runMode']}")
	private String runMode;

	public HomeController() {
		this.setViewRootPath("views/home/");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {

		return viewResult("index");
	}

	@RequestMapping(value = "/res/vc", method = RequestMethod.GET)
	public void AuthImage(HttpServletRequest req, HttpServletResponse res) throws IOException {

		res.setHeader("Pragma", "No-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.setDateHeader("Expires", 0);
		res.setContentType("image/jpeg");

		// 生成随机字串.
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

		// 存入会话session.
		HttpSession session = req.getSession(true);
		session.setAttribute("vcode", verifyCode.toLowerCase());

		// 生成图片.
		int w = 200, h = 80;
		VerifyCodeUtils.outputImage(w, h, res.getOutputStream(), verifyCode);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Integer error, Map<String, Object> map) {

		if (error != null) {
			if (error == 1) {
				map.put("message", "用户或密码错误。");
			}
		}

		map.put("runMode", runMode);
		return viewResult("login");
	}

	@RequestMapping(value = "/portal", method = RequestMethod.GET)
	public String portal() {

		return viewResult("portal");
	}

}
