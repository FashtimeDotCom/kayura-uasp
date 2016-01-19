/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.uasp.service.UserService;
import org.kayura.uasp.po.User;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.StringUtils;
import org.kayura.web.BaseController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		this.setViewRootPath("views/admin/");
	}

	@RequestMapping(value = { "user/", "user/list" }, method = RequestMethod.GET)
	public String userList() {

		return viewResult("user/list");
	}

	@RequestMapping(value = "user/find")
	public String userFind(HttpServletRequest req, Map<String, Object> map, String keyword, String status) {

		PageParams pageParams = ui.getPageParams(req);

		Integer[] intStatus = StringUtils.toInteger(status);
		PageList<User> users = userService.findUsers(keyword, intStatus, pageParams);
		ui.putData(map, users);

		return viewResult("user/find");
	}

	@RequestMapping(value = "user/new", method = RequestMethod.GET)
	public String userNew(HttpServletRequest req, Map<String, Object> map, String id) {

		User userVo = new User();
		userVo.setUserId(KeyUtils.newId());

		map.put("isNew", true);
		map.put("model", userVo);

		return viewResult("user/edit");
	}

	@RequestMapping(value = "user/edit/{id}", method = RequestMethod.GET)
	public String userEdit(HttpServletRequest req, Map<String, Object> map, String id) {

		User userVo = userService.getUserById(id);

		map.put("isNew", false);
		map.put("model", userVo);

		return viewResult("user/edit");
	}

	@RequestMapping(value = "user/save", method = RequestMethod.POST)
	public void userSave(Map<String, Object> map, final User user) {

		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult postResult) {
				GeneralResult result = userService.createNewUser(user);
				postResult.setResult(result);
			}
		});
	}

}
