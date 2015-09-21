/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.web;

import org.kayura.cache.Cache;
import org.kayura.uasp.auth.service.UserService;
import org.kayura.uasp.web.model.UserModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author liangxia@live.com
 */
public class SessionContext {

	private Cache cache;
	private UserService accountService;

	public UserModel getSessionUser(){

		UserModel user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			auth.getPrincipal();
		}
		
		return user;
	}
}
