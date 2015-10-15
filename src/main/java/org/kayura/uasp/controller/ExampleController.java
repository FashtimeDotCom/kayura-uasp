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

	
	@RequestMapping(value = "general/basiclist", method = RequestMethod.GET)
	public String generalbasiclist(){
		return viewResult("general/basiclist");
	}
	

	@RequestMapping(value = "easyui/datagrid", method = RequestMethod.GET)
	public String datagrid() {
		return viewResult("easyui/datagrid/index");
	}

	@RequestMapping(value = "easyui/datagridbasic", method = RequestMethod.GET)
	public String datagridbasic() {
		return viewResult("easyui/datagrid/basic");
	}

	@RequestMapping(value = "easyui/datagridcacheeditor", method = RequestMethod.GET)
	public String datagridcacheeditor() {
		return viewResult("easyui/datagrid/cacheeditor");
	}

	@RequestMapping(value = "easyui/datagridcellediting", method = RequestMethod.GET)
	public String datagridcellediting() {
		return viewResult("easyui/datagrid/cellediting");
	}

	@RequestMapping(value = "easyui/datagridcellstyle", method = RequestMethod.GET)
	public String datagridcellstyle() {
		return viewResult("easyui/datagrid/cellstyle");
	}

	@RequestMapping(value = "easyui/datagridcheckbox", method = RequestMethod.GET)
	public String datagridcheckbox() {
		return viewResult("easyui/datagrid/checkbox");
	}

	@RequestMapping(value = "easyui/datagridcolumngroup", method = RequestMethod.GET)
	public String datagridcolumngroup() {
		return viewResult("easyui/datagrid/columngroup");
	}

	@RequestMapping(value = "easyui/datagridcomplextoolbar", method = RequestMethod.GET)
	public String datagridcomplextoolbar() {
		return viewResult("easyui/datagrid/complextoolbar");
	}
	
	
}
