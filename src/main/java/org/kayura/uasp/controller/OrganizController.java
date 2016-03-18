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
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.po.OrganizItem;
import org.kayura.uasp.service.OrganizService;
import org.kayura.utils.StringUtils;
import org.kayura.web.BaseController;
import org.kayura.web.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 组织机构控制器.
 *
 * @author liangxia@live.com
 */
@Controller
@RequestMapping("/org")
public class OrganizController extends BaseController {

	static final String NULL = "NULL";

	@Autowired
	private OrganizService organizService;

	public OrganizController() {
		this.setViewRootPath("views/org/");
	}

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView fileUpload() {

		ModelAndView mv = this.view("manager");

		return mv;
	}

	/**
	 * 获取组织机构树型数据.
	 * 
	 * @param id 值为 null 或 "" 时，获取所有树型数据。 值为 "NULL" 时，仅获取第一层节点数据。 值为 key 时，获取该
	 *            key 下级子节点。
	 */
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
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

				Result<List<OrganizItem>> r = organizService.findOrgTree(user.getTenantId(), parentId);
				List<TreeNode> roots = new ArrayList<TreeNode>();

				if (r.isSucceed()) {
					List<OrganizItem> items = r.getData();

					if (StringUtils.isEmpty(id) || NULL.equals(id)) {

						TreeNode root = new TreeNode();
						root.setId("ROOT");
						root.setText("所有组织机构");
						root.setState(TreeNode.STATE_OPEN);
						root.setIconCls("icon-organiz");
						roots.add(root);

						List<OrganizItem> rootItems = items.stream().filter(c -> c.getParentId() == null)
								.collect(Collectors.toList());
						for (OrganizItem f : rootItems) {

							TreeNode n = createNode(f);
							root.getChildren().add(n);
							appendChildFolders(n, items);
						}
					} else {

						for (OrganizItem f : items) {
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

	TreeNode createNode(OrganizItem item) {

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

	void appendChildFolders(TreeNode node, List<OrganizItem> items) {

		List<OrganizItem> childs = items.stream()
				.filter(c -> c.getParentId() != null && c.getParentId().equals(node.getId()))
				.sorted((x, y) -> Integer.compare(y.getOrgType(), x.getOrgType())).collect(Collectors.toList());

		if (!childs.isEmpty()) {
			for (OrganizItem f : childs) {

				TreeNode n = createNode(f);
				node.getChildren().add(n);
				appendChildFolders(n, items);
			}
		}
	}

	/**
	 * 获取组织机构树型数据.
	 * 
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
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

				Result<PageList<OrganizItem>> r = 
						organizService.findOrgItems(user.getTenantId(), parentId, keyword, pp);
				ps.setResult(r.getCode(), r.getMessage(), ui.genPageData(r.getData()));
			}
		});
	}
}
