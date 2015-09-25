/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liangxia@live.com
 */
@Controller
public class HomeController extends BaseController {

	public HomeController() {
		this.setViewRootPath("home/");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {

		return viewResult("index");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return viewResult("login");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {

		return viewResult("logout");
	}

	@RequestMapping(value = "/portal", method = RequestMethod.GET)
	public String portal() {

		return viewResult("portal");
	}
}
