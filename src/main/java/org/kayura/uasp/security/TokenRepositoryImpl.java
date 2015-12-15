/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.security;

import java.util.Date;

import org.kayura.uasp.service.RememberMeService;
import org.kayura.uasp.vo.RememberMeVo;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class TokenRepositoryImpl implements PersistentTokenRepository {

	private RememberMeService rememberMeService;

	public void setRememberMeService(RememberMeService rememberMeService) {
		this.rememberMeService = rememberMeService;
	}

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		
		RememberMeVo vo = new RememberMeVo();
		vo.setToken(token.getTokenValue());
		vo.setSeriesId(token.getSeries());
		vo.setLastUsed(token.getDate());
		vo.setUserId(token.getUsername());
		
		rememberMeService.createNewToken(vo);		
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		
		rememberMeService.updateToken(series, tokenValue, lastUsed);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {

		RememberMeVo vo = rememberMeService.getTokenForSeries(seriesId);
		
		PersistentRememberMeToken token = new PersistentRememberMeToken(
				vo.getUserId(), vo.getSeriesId(), vo.getToken(), vo.getLastUsed());
		
		return token;
	}

	@Override
	public void removeUserTokens(String username) {

		rememberMeService.removeUserTokens(username);
	}

}
