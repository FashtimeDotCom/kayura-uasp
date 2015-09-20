/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.dao;

import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.uasp.po.Account;
import org.springframework.stereotype.Repository;

/**
 * @author liangxia@live.com
 */
@Repository
public interface AccountMapper {
	
	PageList<Account> findAccountsByMap(Map<String, Object> args, PageBounds pageBounds);
	
	boolean verify(String loginName, String hashPassword);

	void insertAccount(Account account);
	
	void updateAccount(Account account);

	void deleteAccount(String accountId);
}
