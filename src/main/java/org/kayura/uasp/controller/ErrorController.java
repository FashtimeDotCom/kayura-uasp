/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ErrorController
 *
 * @author liangxia@live.com
 */
@Controller
public class ErrorController extends BaseController {

	@RequestMapping(value = "/error/403", method = RequestMethod.GET)
	public ModelAndView denied() {

		return view("views/error/403");
	}

	@RequestMapping(value = "/error/404", method = RequestMethod.GET)
	public ModelAndView notfound() {

		return view("views/error/404");
	}
}
