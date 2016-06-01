/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.web.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * MockController
 *
 * @author liangxia@live.com
 */
@Controller
public class MockController extends BaseController {

	@RequestMapping(value = "/mock/fileup", method = RequestMethod.GET)
	public ModelAndView fileUpload() {

		return this.view("views/mock/fileup");
	}

}
