/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service;

import java.util.List;

import org.kayura.type.Result;
import org.kayura.uasp.po.OrganizItem;

/**
 * @author liangxia@live.com
 */
public interface OrganizService {

	/**
	 * 查询组织机构树型数据.
	 * 
	 * @param tenantId 租户Id,必需.
	 * @param parentId 可选值为 'NULL', null, 值.
	 * @return
	 */
	Result<List<OrganizItem>> findOrgTree(String tenantId, String parentId);

}
