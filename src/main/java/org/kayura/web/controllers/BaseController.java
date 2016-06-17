/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.kayura.type.Result;
import org.kayura.web.rest.RestResource;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liangxia@live.com
 */
public abstract class BaseController extends RestResource {

	public ModelAndView errorPage(Exception ex) {

		return error(ex.getMessage(), ex.toString());
	}

	public ModelAndView error(Result<?> r) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", r.getMessage());
		return error(map);
	}

	public ModelAndView error(String message, String details) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("details", details);

		return error(map);
	}

	public ModelAndView error(Map<String, Object> map) {

		return new ModelAndView("shared/error", map);
	}

	public ModelAndView view(String viewName) {

		return new ModelAndView(viewName);
	}

	public ModelAndView view(String viewName, Object model) {

		ModelAndView mv = view(viewName);
		mv.addObject("model", model);

		return mv;
	}
}