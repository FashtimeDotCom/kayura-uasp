/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.dao.OrganizeMapper;
import org.kayura.uasp.po.Company;
import org.kayura.uasp.po.OrganizeItem;
import org.kayura.uasp.service.OrganizeService;
import org.kayura.utils.DateUtils;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 组织机构服务实现类.
 *
 * @author liangxia@live.com
 */
@Service
public class OrganizServiceImpl implements OrganizeService {

	@Autowired
	private OrganizeMapper organizMapper;

	public Result<List<OrganizeItem>> findOrgTree(String tenantId, String parentId) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("tenantId", tenantId);
		args.put("parentId", parentId);

		List<OrganizeItem> items = organizMapper.findOrgTree(args);

		return new Result<List<OrganizeItem>>(Result.SUCCEED, items);
	}

	@Override
	public Result<PageList<OrganizeItem>> findOrgItems(String tenantId, String parentId, String keyword,
			PageParams pageParams) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("tenantId", tenantId);

		if (!StringUtils.isEmpty(parentId)) {
			args.put("parentId", parentId);
		}

		if (!StringUtils.isEmpty(keyword)) {
			args.put("keyword", "%" + keyword + "%");
		}

		PageList<OrganizeItem> items = organizMapper.findOrgItems(args, new PageBounds(pageParams));

		return new Result<PageList<OrganizeItem>>(Result.SUCCEED, items);
	}

	@Override
	public GeneralResult removeOrgItem(String orgId) {

		return null;
	}

	@Override
	public Result<Company> getCompanyById(String id) {

		Company company = organizMapper.getCompanyById(id);
		if (company == null) {
			return new Result<Company>(Result.FAILED, "公司 id: " + id + " 不存在。");
		}

		return new Result<Company>(Result.SUCCEED, company);
	}

	@Override
	public GeneralResult insertCompany(Company company) {

		if (StringUtils.isEmpty(company.getCompanyId())) {
			company.setCompanyId(KeyUtils.newId());
		}

		if (company.getStatus() == null) {
			company.setStatus(Company.STATUS_ENABLED);
		}

		company.setUpdatedTime(DateUtils.now());
		organizMapper.insertCompany(company);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", company.getCompanyId());

		GeneralResult r = Result.succeed();
		r.setData(data);

		return r;
	}

	@Override
	public GeneralResult updateCompany(Company company) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("companyId", company.getCompanyId());
		args.put("code", company.getCode());
		args.put("shortName", company.getShortName());
		args.put("fullName", company.getFullName());
		args.put("description", company.getDescription());
		args.put("industryTypeId", company.getIndustryTypeId());
		args.put("address", company.getAddress());
		args.put("Postcode", company.getPostcode());
		args.put("telephone", company.getTelephone());
		args.put("email", company.getEmail());
		args.put("fax", company.getFax());
		args.put("linkman", company.getLinkman());
		args.put("estaDate", company.getEstaDate());
		args.put("serial", company.getSerial());
		args.put("status", company.getStatus());
		args.put("updatedTime", DateUtils.now());

		organizMapper.updateCompany(args);

		return Result.succeed();
	}

	@Override
	public GeneralResult deleteCompany(String companyId) {

		organizMapper.deleteCompany(companyId);
		return Result.succeed();
	}

}
