/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kayura.web.BaseController;
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
	
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(MockController.class);
	
	public MockController() {
		this.setViewRootPath("views/mock/");
	}
	
	@RequestMapping(value = "/mock/fileup", method = RequestMethod.GET)
	public ModelAndView fileUpload() {

		return this.view("fileup");
	}

}
