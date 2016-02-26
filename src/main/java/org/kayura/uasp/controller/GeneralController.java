/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.web.BaseController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * GeneralController
 *
 * @author liangxia@live.com
 */
@Controller
@RequestMapping("/gm")
public class GeneralController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(GeneralController.class);


	public GeneralController() {
		this.setViewRootPath("views/general/");
	}

}
