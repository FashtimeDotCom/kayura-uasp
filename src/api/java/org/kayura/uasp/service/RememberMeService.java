/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service;

import java.util.Date;

import org.kayura.uasp.vo.RememberMeVo;

/**
 * RememberMeService.
 * 
 * @author liangxia@live.com
 */
public interface RememberMeService {

	public void createNewToken(RememberMeVo rememberMe);

	/**
	 * args: String seriesId, String token, Date lastUsed
	 */
	public void updateToken(String seriesId, String token, Date lastUsed);

	public RememberMeVo getTokenForSeries(String seriesId);

	public void removeUserTokens(String userId);

}
