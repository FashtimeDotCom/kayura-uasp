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
import org.kayura.uasp.po.Department;
import org.kayura.uasp.po.OrganizeItem;
import org.kayura.uasp.po.Position;

/**
 * OrganizMapper
 *
 * @author liangxia@live.com
 */
public interface OrganizeMapper extends BaseDao {

	// Organize
	
	/**
	 * 查询组织机构树型数据.
	 * 
	 * @param args [tenantId] 必需, [parentId] 可选值为 'NULL', null, 值.
	 * @return
	 */
	List<OrganizeItem> findOrgTree(Map<String, Object> args);

	/**
	 * 查询组织机构树型数据.
	 * 
	 * @param args [tenantId] 必需, [parentId] 可选值.
	 * @param pageBounds
	 * @return
	 */
	PageList<OrganizeItem> findOrgItems(Map<String, Object> args, PageBounds pageBounds);

	// Company

	/**
	 * 查询符条件的公司信息.
	 * @param args 支持的参数：companyId,parentId,tenantId,status.
	 * @return
	 */
	List<Company> findCompanies(Map<String, Object> args);

	/**
	 * 插入一个公司信息至数据库.
	 * @param company 数据库实体对象.
	 */
	void insertCompany(Company company);

	/**
	 * 更新一个公司信息至数据库.
	 */
	void updateCompany(Map<String, Object> args);

	void deleteCompany(Map<String, Object> args);

	// Department

	List<Department> findDepartments(Map<String, Object> args);

	void insertDepartment(Department department);

	void updateDepartment(Map<String, Object> args);

	void deleteDepartment(Map<String, Object> args);

	// Position

	List<Position> findPositions(Map<String, Object> args);

	void insertPosition(Position position);

	void updatePosition(Map<String, Object> args);

	void deletePosition(Map<String, Object> args);

}