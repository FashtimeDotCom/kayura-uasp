/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.po.User;
import org.kayura.uasp.service.UserService;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.StringUtils;
import org.kayura.web.BaseController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liangxia@live.com
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AdminController() {
		this.setViewRootPath("views/admin/");
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "user/list", method = RequestMethod.GET)
	public String userList() {

		return viewResult("user/list");
	}

	@RequestMapping(value = "user/find", method = RequestMethod.POST)
	public void userFind(HttpServletRequest req, Map<String, Object> map, String keyword, String status) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				PageParams pageParams = ui.getPageParams(req);

				String tenantId = getLoginUser().getTenantId();
				Integer[] intStatus = StringUtils.toInteger(status);

				Result<PageList<User>> r = userService.findUsers(tenantId, keyword, intStatus, pageParams);
				ps.setCode(r.getCode());
				if (r.isSucceed()) {
					ps.add("items", ui.genPageData(r.getData()));
				} else {
					ps.addMessage(r.getMessage());
				}
			}
		});
	}

	@RequestMapping(value = "user/new", method = RequestMethod.GET)
	public String userNew(HttpServletRequest req, Map<String, Object> map, String id) {

		User user = new User();
		user.setRoles("USER");
		user.setIsEnabled(true);
		user.setIsLocked(false);
		map.put("model", user);

		return viewResult("user/edit");
	}

	@RequestMapping(value = "user/edit", method = RequestMethod.GET)
	public String userEdit(HttpServletRequest req, Map<String, Object> map, String id) {

		User user = userService.getUserById(id);
		user.setPassword("");
		map.put("model", user);

		return viewResult("user/edit");
	}

	@RequestMapping(value = "user/save", method = RequestMethod.POST)
	public void userSave(Map<String, Object> map, User user) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				if (StringUtils.isEmpty(user.getUserId())) {

					String tenantId = getLoginUser().getTenantId();
					String salt = KeyUtils.random();
					String hash = passwordEncoder.encodePassword(user.getPassword(), salt);

					user.setUserId(KeyUtils.newId());
					user.setTenantId(tenantId);
					user.setSalt(salt);
					user.setPassword(hash);

					GeneralResult result = userService.createNewUser(user);
					ps.setResult(result);
				} else {

					GeneralResult result = userService.updateUserInfo(user);
					ps.setResult(result);
				}
			}
		});
	}

}
