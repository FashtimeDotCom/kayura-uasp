/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kayura.type.Result;
import org.kayura.uasp.dao.OrganizMapper;
import org.kayura.uasp.po.OrganizItem;
import org.kayura.uasp.service.OrganizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 组织机构服务实现类.
 *
 * @author liangxia@live.com
 */
@Service
public class OrganizServiceImpl implements OrganizService {

	@Autowired
	private OrganizMapper organizMapper;

	public Result<List<OrganizItem>> findOrgTree(String tenantId, String parentId) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("tenantId", tenantId);
		args.put("parentId", parentId);

		List<OrganizItem> items = organizMapper.findOrgTree(args);

		return new Result<List<OrganizItem>>(Result.SUCCEED, items);
	}

}
