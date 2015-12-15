/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.kayura.uasp.dao.RememberMeMapper;
import org.kayura.uasp.po.RememberMe;
import org.kayura.uasp.service.RememberMeService;
import org.kayura.uasp.vo.RememberMeVo;
import org.kayura.uasp.vo.convert.RememberMeConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 站点自动登录持久化服务.
 * 
 * @author liangxia@live.com
 */
@Service
public class RememberMeServiceImpl implements RememberMeService {

	@Autowired
	private RememberMeMapper rememberMeMapper;

	@Override
	public void createNewToken(RememberMeVo rememberMe) {
		
		RememberMe entity = RememberMeConvert.toEntity(rememberMe);
		rememberMeMapper.createNewToken(entity);
	}

	@Override
	public void updateToken(String seriesId, String token, Date lastUsed) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("seriesId", seriesId);
		args.put("token", token);
		args.put("lastUsed", lastUsed);
		
		rememberMeMapper.updateToken(args);
	}

	@Override
	public RememberMeVo getTokenForSeries(String seriesId) {

		RememberMe entity = rememberMeMapper.getTokenForSeries(seriesId);
		return RememberMeConvert.toVo(entity);
	}

	@Override
	public void removeUserTokens(String userId) {
		
		rememberMeMapper.removeUserTokens(userId);
	}

}
