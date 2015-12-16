/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.dao;

import java.util.Map;

import org.kayura.core.BaseDao;
import org.kayura.uasp.po.RememberMe;

public interface RememberMeMapper extends BaseDao  {

	public void createNewToken(RememberMe rememberMe);

	/**
	 * args: String seriesId, String token, Date lastUsed
	 */
	public void updateToken(Map<String, Object> args);

	public RememberMe getTokenForSeries(String seriesId);

	public void removeUserTokens(String userId);

}
