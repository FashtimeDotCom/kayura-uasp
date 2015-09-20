/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.uasp.dao.AccountMapper;
import org.kayura.uasp.po.Account;
import org.kayura.uasp.services.AccountService;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author liangxia@live.com
 */
@Service
public class AccountServiceImpl implements AccountService {

	private AccountMapper accountMapper;

	@Override
	public PageList<Account> findAccounts(String keyword, Integer[] status, PageParams pageParams) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("keyword", keyword);
		args.put("status", StringUtils.join(",", status));

		return accountMapper.findAccountsByMap(args, new PageBounds(pageParams));
	}

	@Override
	public boolean verify(String userName, String password) {

		boolean isOk = accountMapper.verify(userName, password);
		return isOk;
	}

	@Override
	public void saveOrUpdateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(String accountId) {
		// TODO Auto-generated method stub
		
	}
}
