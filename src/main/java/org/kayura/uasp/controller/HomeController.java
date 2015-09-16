/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.uasp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liangxia@live.com
 */
@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {

		return "home/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "home/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public boolean verifyUser(String userName, String password) {

		return userService.verifyUser(userName, password);
	}

}
