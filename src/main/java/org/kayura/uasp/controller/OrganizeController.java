/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.po.Company;
import org.kayura.uasp.po.Department;
import org.kayura.uasp.po.OrganizeItem;
import org.kayura.uasp.po.Position;
import org.kayura.uasp.service.OrganizeService;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.StringUtils;
import org.kayura.web.BaseController;
import org.kayura.tags.easyui.types.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 组织机构控制器.
 *
 * @author liangxia@live.com
 */
@Controller
public class OrganizeController extends BaseController {

	static final String NULL = "NULL";

	@Autowired
	private OrganizeService writerOrganizeService;

	@Autowired
	private OrganizeService readerOrganizeService;

	@RequestMapping(value = "/org/manager", method = RequestMethod.GET)
	public ModelAndView fileUpload() {

		ModelAndView mv = this.view("views/org/manager");

		return mv;
	}

	/**
	 * 获取组织机构树型数据.
	 * 
	 * @param id 值为 null 或 "" 时，获取所有树型数据。 值为 "NULL" 时，仅获取第一层节点数据。 值为 key 时，获取该
	 *            key 下级子节点。
	 */
	@RequestMapping(value = "/org/tree", method = RequestMethod.POST)
	public void orgTree(Map<String, Object> map, String id) {

		LoginUser user = this.getLoginUser();

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				String parentId = id;
				if (!StringUtils.isEmpty(id)) {
					parentId = id.toUpperCase();
				} else {
					parentId = null;
				}

				List<TreeNode> roots = new ArrayList<TreeNode>();

				Result<List<OrganizeItem>> r = readerOrganizeService.findOrgTree(user.getTenantId(), parentId);
				if (r.isSucceed()) {

					List<OrganizeItem> items = r.getData();
					if (StringUtils.isEmpty(id) || NULL.equals(id)) {

						TreeNode root = new TreeNode();
						root.setId("ROOT");
						root.setText("所有组织机构");
						root.setState(TreeNode.STATE_OPEN);
						root.setIconCls("icon-organiz");
						root.addAttr("type", 0);
						roots.add(root);

						List<OrganizeItem> rootItems = items.stream().filter(c -> c.getParentId() == null)
								.collect(Collectors.toList());
						for (OrganizeItem f : rootItems) {

							TreeNode n = createNode(f);
							root.addNode(n);
							appendChildFolders(n, items);
						}
					} else {

						for (OrganizeItem f : items) {
							roots.add(createNode(f));
						}
					}
				}

				// 添加以返回结果.
				ps.setData(roots);
			}
		});
	}

	String getOrgTreeIcon(int orgType) {

		String iconCls = "icon-folder";
		switch (orgType) {
		case 1:
			iconCls = "icon-company";
			break;
		case 2:
			iconCls = "icon-depart";
			break;
		case 3:
			iconCls = "icon-position";
			break;
		}
		return iconCls;
	}

	TreeNode createNode(OrganizeItem item) {

		TreeNode n = new TreeNode();
		n.setId(item.getOrgId());
		n.setText(item.getDisplayName());
		if (item.getCount() == 0) {
			n.setState(TreeNode.STATE_OPEN);
		} else {
			n.setState(TreeNode.STATE_CLOSED);
		}
		n.setIconCls(getOrgTreeIcon(item.getOrgType()));
		n.addAttr("type", item.getOrgType());

		return n;
	}

	void appendChildFolders(TreeNode node, List<OrganizeItem> items) {

		List<OrganizeItem> childs = items.stream()
				.filter(c -> c.getParentId() != null && c.getParentId().equals(node.getId()))
				.sorted((x, y) -> Integer.compare(y.getOrgType(), x.getOrgType())).collect(Collectors.toList());
		if (!childs.isEmpty()) {
			for (OrganizeItem f : childs) {

				TreeNode n = createNode(f);
				node.addNode(n);
				appendChildFolders(n, items);
			}
		}
	}

	/**
	 * 获取组织机构树型数据.
	 * 
	 */
	@RequestMapping(value = "/org/find", method = RequestMethod.POST)
	public void findOrgItems(HttpServletRequest req, Map<String, Object> map, String id, String keyword) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				PageParams pp = ui.getPageParams(req);

				String parentId = id;
				if (StringUtils.isEmpty(id)) {
					parentId = null;
				}

				Result<PageList<OrganizeItem>> r = readerOrganizeService.findOrgItems(user.getTenantId(), parentId,
						keyword, pp);
				ps.setResult(r.getCode(), r.getMessage(), ui.genPageData(r.getData()));
			}
		});
	}

	@RequestMapping(value = "/org/remove", method = RequestMethod.POST)
	public void removeOrgItem(HttpServletRequest req, Map<String, Object> map, String id,
			@RequestParam("t") Integer type) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {
				GeneralResult r = writerOrganizeService.removeOrgItem(id, type);
				ps.setResult(r);
			}
		});
	}

	@RequestMapping(value = "/org/company/new", method = RequestMethod.GET)
	public ModelAndView createCompany(@RequestParam("pid") String parentId, @RequestParam("pname") String parentName) {

		Company company = new Company();
		company.setParentId(parentId);
		company.setParentName(parentName);

		ModelAndView mv = this.view("views/org/companyedit");
		mv.addObject("model", company);
		return mv;
	}

	@RequestMapping(value = "/org/company", method = RequestMethod.GET)
	public ModelAndView editCompany(String id) {

		Result<Company> r = readerOrganizeService.getCompanyById(id);
		if (r.isSucceed()) {

			ModelAndView mv = this.view("views/org/companyedit");
			mv.addObject("model", r.getData());
			return mv;
		} else {
			return this.errorPage("编辑公司信息时异常。", r.getMessage());
		}
	}

	@RequestMapping(value = "/org/company/save", method = RequestMethod.POST)
	public void saveCompany(HttpServletRequest req, Map<String, Object> map, Company company) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				GeneralResult r = null;

				if (StringUtils.isEmpty(company.getCompanyId())) {

					LoginUser user = getLoginUser();

					company.setCompanyId(KeyUtils.newId());
					company.setStatus(Company.STATUS_ENABLED);
					company.setTenantId(user.getTenantId());

					r = writerOrganizeService.insertCompany(company);
				} else {

					r = writerOrganizeService.updateCompany(company);
				}

				if (r != null) {
					ps.setResult(r);
				}
			}
		});
	}

	/**
	 * 显示创建一个部门信息页面.
	 * 
	 * @param parentId 新部门的上级ID
	 * @param type 表示上级ID是什么类型: 1 公司;2 部门;
	 * @param parentName 上级组织显示名.
	 * @return
	 */
	@RequestMapping(value = "/org/depart/new", method = RequestMethod.GET)
	public ModelAndView createDepart(@RequestParam("pid") String parentId, @RequestParam("t") Integer type,
			@RequestParam("pname") String parentName) {

		Department department = new Department();

		if (type == OrganizeItem.ORGTYPE_COMPANY) {

			department.setCompanyId(parentId);
			department.setCompanyName(parentName);

		} else if (type == OrganizeItem.ORGTYPE_DEPART) {

			Result<Department> r = readerOrganizeService.getDepartmentById(parentId);
			if (r.isSucceed()) {
				department.setCompanyId(r.getData().getCompanyId());
				department.setCompanyName(r.getData().getCompanyName());
			}

			department.setParentId(parentId);
			department.setParentName(parentName);
		}

		ModelAndView mv = this.view("views/org/departedit");
		mv.addObject("model", department);
		return mv;
	}

	/**
	 * 显示创建一个部门信息页面.
	 * 
	 * @param id 部门ID
	 * @return
	 */
	@RequestMapping(value = "/org/depart", method = RequestMethod.GET)
	public ModelAndView editDepart(String id) {

		Result<Department> r = readerOrganizeService.getDepartmentById(id);
		if (r.isSucceed()) {

			ModelAndView mv = this.view("views/org/departedit");
			mv.addObject("model", r.getData());
			return mv;
		} else {
			return this.errorPage("编辑公司信息时异常。", r.getMessage());
		}
	}

	@RequestMapping(value = "/org/depart/save", method = RequestMethod.POST)
	public void saveDepartment(HttpServletRequest req, Map<String, Object> map, Department department) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				GeneralResult r = null;

				if (StringUtils.isEmpty(department.getDepartmentId())) {

					LoginUser user = getLoginUser();

					department.setDepartmentId(KeyUtils.newId());
					department.setStatus(Company.STATUS_ENABLED);
					department.setTenantId(user.getTenantId());

					r = writerOrganizeService.insertDepartment(department);
				} else {

					r = writerOrganizeService.updateDepartment(department);
				}

				if (r != null) {
					ps.setResult(r);
				}
			}
		});
	}

	/**
	 * 显示创建一个岗位信息页面.
	 * 
	 * @param parentId 新岗位的上级部门Id.
	 * @param parentName 上级部门显示名.
	 * @return
	 */
	@RequestMapping(value = "/org/position/new", method = RequestMethod.GET)
	public ModelAndView createPosition(@RequestParam("pid") String parentId, @RequestParam("pname") String parentName) {

		Position position = new Position();
		position.setDepartmentId(parentId);
		position.setDepartmentName(parentName);

		ModelAndView mv = this.view("views/org/positionedit");
		mv.addObject("model", position);
		return mv;
	}

	/**
	 * 显示创建一个部门信息页面.
	 * 
	 * @param id 显示的岗位Id.
	 * @return
	 */
	@RequestMapping(value = "/org/position", method = RequestMethod.GET)
	public ModelAndView editPosition(String id) {

		Result<Position> r = readerOrganizeService.getPositionById(id);
		if (r.isSucceed()) {

			ModelAndView mv = this.view("views/org/departedit");
			mv.addObject("model", r.getData());
			return mv;
		} else {
			return this.errorPage("编辑岗位信息时异常。", r.getMessage());
		}
	}
}
