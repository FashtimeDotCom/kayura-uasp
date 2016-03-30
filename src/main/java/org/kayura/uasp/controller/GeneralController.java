/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.web.BaseController;

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

	public GeneralController() {
		this.setViewRootPath("views/general/");
	}

}
