/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.web.controller;

import org.kayura.uasp.auth.service.UserService;
import org.kayura.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liangxia@live.com
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@Autowired
	private UserService userService;

	public AdminController() {
		this.setViewRootPath("admin/account/");
	}

	@RequestMapping("list")
	public String list() {

		return viewResult("list");
	}

}
