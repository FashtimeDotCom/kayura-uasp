/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.dao;

import org.springframework.stereotype.Repository;

/**
 * @author liangxia@live.com
 */
@Repository
public interface UserMapper {
	
	boolean verifyUser(String userName, String hashPassword);

}
