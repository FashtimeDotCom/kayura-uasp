/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.uasp.service.UserService;
import org.kayura.uasp.vo.UserVo;
import org.kayura.utils.StringUtils;
import org.kayura.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liangxia@live.com
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@Autowired
	private UserService userService;

	public AdminController() {
		this.setViewRootPath("admin/");
	}

	@RequestMapping(value = "user/list", method = RequestMethod.GET)
	public String userList() {

		return viewResult("user/list");
	}

	@RequestMapping(value = "user/find")
	public String loadUserData(HttpServletRequest req, Map<String, Object> map, String keyword,
			String status) {

		PageParams pageParams = ui.getPageParams(req);

		Integer[] intStatus = StringUtils.toInteger(status);
		PageList<UserVo> users = userService.findUsers(keyword, intStatus, pageParams);

		ui.putData(map, users);

		return viewResult("user/find");
	}

}
