/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.web;

import org.kayura.cache.Cache;
import org.kayura.uasp.service.UserService;
import org.kayura.uasp.vo.UserVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author liangxia@live.com
 */
public class SessionContext {

	private Cache cache;
	private UserService accountService;

	public UserVo getSessionUser() {

		UserVo user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			auth.getPrincipal();
		}

		return user;
	}
}
