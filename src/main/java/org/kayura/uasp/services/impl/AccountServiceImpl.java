/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.services.impl;

import org.kayura.uasp.dao.AccountMapper;
import org.kayura.uasp.services.AccountService;
import org.springframework.stereotype.Service;

/**
 * @author liangxia@live.com
 */
@Service
public class AccountServiceImpl implements AccountService {

	private AccountMapper accountMapper;

	public boolean verify(String userName, String password) {

		boolean isOk = accountMapper.verify(userName, password);
		return isOk;
	}
	
}
