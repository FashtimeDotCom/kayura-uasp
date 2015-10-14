package org.kayura.uasp.controller;

import org.kayura.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/example")
public class ExampleController extends BaseController {

	public ExampleController() {
		this.setViewRootPath("example/");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return viewResult("index");
	}
	
	@RequestMapping(value = "/htmlconvert", method = RequestMethod.GET)
	public String htmlconvert() {
		return viewResult("htmlconvert");
	}
	
	@RequestMapping(value = "easyui/datagrid", method = RequestMethod.GET)
	public String datagrid(){
		return viewResult("easyui/datagrid/index");
	}
	
	@RequestMapping(value = "easyui/datagridbasic", method = RequestMethod.GET)
	public String datagridbasic(){
		return viewResult("easyui/datagrid/basic");
	}
	
	@RequestMapping(value = "easyui/datagridcacheeditor", method = RequestMethod.GET)
	public String datagridcacheeditor(){
		return viewResult("easyui/datagrid/cacheeditor");
	}	
}
