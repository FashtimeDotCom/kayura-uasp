package org.kayura.formbuilder.controller;

import org.kayura.web.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormBuilderController extends BaseController {

	@RequestMapping(value = "/formbuilder/mobile", method = RequestMethod.GET)
	public ModelAndView index() {

		ModelAndView view = this.view("views/formbuilder/mobile");
		return view;
	}

}
