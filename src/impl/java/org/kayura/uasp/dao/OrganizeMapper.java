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

	List<Company> findCompanies(Map<String, Object> args);

	void insertCompany(Company company);

	void updateCompany(Map<String, Object> args);

	void deleteCompany(String companyId);

	// Department

	List<Department> findDepartments(Map<String, Object> args);

	void insertDepartment(Department department);

	void updateDepartment(Map<String, Object> args);

	void deleteDepartment(String departmentId);

	// Position

	List<Position> findPositions(Map<String, Object> args);

	void insertPosition(Position department);

	void updatePosition(Map<String, Object> args);

	void deletePosition(String positionId);

}
