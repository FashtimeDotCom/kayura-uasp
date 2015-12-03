/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.security;

import org.kayura.uasp.service.UserService;
import org.kayura.uasp.vo.UserVo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {

	private static final Log logger = LogFactory.getLog(UserDetailServiceImpl.class);

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		UserDetails user = null;

		try {
			UserVo userVo = userService.getUserByUserName(username);

			user = new User(username, userVo.getPassword(), userVo.getIsEnabled(), true, true, true,
					getAuthorities(userVo.getAuthType()));

		} catch (Exception e) {
			logger.error("Error in retrieving user", e);
		}

		return user;
	}

	public Collection<GrantedAuthority> getAuthorities(String roles) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		logger.debug("Grant ROLE_USER to this user");
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (roles.contains("ADMIN")) {
			logger.debug("Grant ROLE_ADMIN to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return authList;
	}

}