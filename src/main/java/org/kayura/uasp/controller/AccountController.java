/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.spring.webmvc.BaseController;
import org.kayura.uasp.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liangxia@live.com
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

	private AccountService accountService;

	public AccountController() {
		this.setViewRootPath("admin/account/");
	}

	@RequestMapping("list")
	public String list() {

		return viewResult("list");
	}

}
