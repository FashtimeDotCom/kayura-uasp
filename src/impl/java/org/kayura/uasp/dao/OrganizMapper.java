/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.dao;

import java.util.List;
import java.util.Map;

import org.kayura.core.BaseDao;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.uasp.po.Company;
import org.kayura.uasp.po.OrganizItem;

/**
 * OrganizMapper
 *
 * @author liangxia@live.com
 */
public interface OrganizMapper extends BaseDao {

	/**
	 * 查询组织机构树型数据.
	 * 
	 * @param args [tenantId] 必需, [parentId] 可选值为 'NULL', null, 值.
	 * @return
	 */
	List<OrganizItem> findOrgTree(Map<String, Object> args);

	/**
	 * 查询组织机构树型数据.
	 * 
	 * @param args [tenantId] 必需, [parentId] 可选值.
	 * @param pageBounds
	 * @return
	 */
	PageList<OrganizItem> findOrgItems(Map<String, Object> args, PageBounds pageBounds);

	Company getCompanyById(String id);

	void insertCompany(Company company);

	void updateCompany(Map<String, Object> args);
	
	void deleteCompany(String companyId);
}
