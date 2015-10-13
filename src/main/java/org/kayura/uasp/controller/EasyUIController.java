package org.kayura.uasp.controller;

import org.kayura.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/easyui")
public class EasyUIController extends BaseController {

	public EasyUIController() {
		this.setViewRootPath("easyui/");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return viewResult("index");
	}

	@RequestMapping(value = "/datagrid/basic", method = RequestMethod.GET)
	public String datagridbasic(){
		return viewResult("datagrid/basic");
	}
}
