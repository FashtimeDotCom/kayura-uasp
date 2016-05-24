/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.util.Map;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.type.GeneralResult;
import org.kayura.type.Result;
import org.kayura.uasp.po.Role;
import org.kayura.uasp.service.AuthorityService;
import org.kayura.utils.StringUtils;
import org.kayura.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * AuthController
 *
 * @author liangxia@live.com
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

	@Autowired
	private AuthorityService readerAuthorityService;

	@Autowired
	private AuthorityService writerAuthorityService;

	@RequestMapping(value = "role/list", method = RequestMethod.GET)
	public ModelAndView roleList() {

		return view("auth/role-list");
	}

	@RequestMapping(value = "role/new", method = RequestMethod.GET)
	public ModelAndView createRole() {

		Role model = new Role();
		return view("auth/role-edit", model);
	}

	@RequestMapping(value = "role/edit", method = RequestMethod.GET)
	public ModelAndView editRole(String id) {

		Result<Role> r = readerAuthorityService.getRoleById(id);
		if (r.isSucceed()) {
			return view("auth/role-edit", r.getData());
		} else {
			return error(r);
		}
	}

	@RequestMapping(value = "saverole", method = RequestMethod.POST)
	public void saveRole(Map<String, Object> map, Role model) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				GeneralResult r = null;

				if (StringUtils.isEmpty(model.getRoleId())) {

					LoginUser user = getLoginUser();

					model.setEnabled(true);
					model.setTenantId(user.getTenantId());

					r = writerAuthorityService.createRole(model);
				} else {

					r = writerAuthorityService.updateRole(model);
				}

				if (r != null) {
					ps.setResult(r);
				}
			}
		});
	}

}
