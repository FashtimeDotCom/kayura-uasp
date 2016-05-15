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
 * BpmController
 *
 * @author liangxia@live.com
 */
@Controller
@RequestMapping("/bpm")
public class BpmController extends BaseController {

	@RequestMapping(value = "/tasklist", method = RequestMethod.GET)
	public ModelAndView taskList() {

		return view("views/bpm/tasklist");
	}

}
