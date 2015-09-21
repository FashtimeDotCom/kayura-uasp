/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kayura.logging.Log;
import org.kayura.logging.LogFactory;
import org.kayura.uasp.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	protected static Log logger = LogFactory.getLog(UserDetailServiceImpl.class);

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		UserDetails user = null;

		try {

			// UserVo dbUser = accountService.getUserByName(username);

			user = new User("admin", "123456", true, true, true, true,
					getAuthorities("ADMIN"));

		} catch (Exception e) {
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
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