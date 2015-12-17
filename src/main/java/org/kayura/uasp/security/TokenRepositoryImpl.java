/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.security;

import java.util.Date;

import org.kayura.uasp.service.UserService;
import org.kayura.uasp.vo.AutoLoginVo;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class TokenRepositoryImpl implements PersistentTokenRepository {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		
		AutoLoginVo vo = new AutoLoginVo();
		vo.setToken(token.getTokenValue());
		vo.setSeriesId(token.getSeries());
		vo.setLastUsed(token.getDate());
		vo.setUserId(token.getUsername());
		
		userService.createLoginToken(vo);		
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		
		userService.updateLoginToken(series, tokenValue, lastUsed);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {

		AutoLoginVo vo = userService.getLoginTokenById(seriesId);
		
		PersistentRememberMeToken token = new PersistentRememberMeToken(
				vo.getUserId(), vo.getSeriesId(), vo.getToken(), vo.getLastUsed());
		
		return token;
	}

	@Override
	public void removeUserTokens(String username) {

		userService.removeLoginTokensByUser(username);
	}

}
