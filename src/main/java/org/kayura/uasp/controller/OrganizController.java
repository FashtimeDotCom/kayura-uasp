/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
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

	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	public void orgTree(Map<String, Object> map, String id) {

		LoginUser user = this.getLoginUser();

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				String parentId = id;
				if (!StringUtils.isEmpty(id)) {
					parentId = id.toUpperCase();
				}

				Result<List<OrganizItem>> r = organizService.findOrgTree(user.getTenantId(), parentId);
				List<TreeNode> roots = new ArrayList<TreeNode>();

				if (r.isSucceed()) {
					List<OrganizItem> items = r.getData();

					if (StringUtils.isEmpty(id)) {

						TreeNode root = new TreeNode();
						root.setId("NULL");
						root.setText("所有组织机构");
						root.setIconCls("icon-book");
						roots.add(root);

						List<OrganizItem> rootItems = items.stream().filter(c -> c.getParentId() == null)
								.collect(Collectors.toList());
						for (OrganizItem f : rootItems) {

							TreeNode n = new TreeNode();
							n.setId(f.getOrgId());
							n.setText(f.getDisplayName());
							n.setState(TreeNode.STATE_OPEN);
							n.setIconCls("icon-folder");
							root.getChildren().add(n);

							appendChildFolders(n, items);
						}
					} else {

						for (OrganizItem f : items) {

							TreeNode n = new TreeNode();
							n.setId(f.getOrgId());
							n.setText(f.getDisplayName());
							n.setState(TreeNode.STATE_OPEN);
							n.setIconCls("icon-folder");
							roots.add(n);
						}
					}
				}

				// 添加以返回结果.
				ps.setData(roots);
			}
		});
	}

	void appendChildFolders(TreeNode node, List<OrganizItem> items) {

		List<OrganizItem> childs = items.stream()
				.filter(c -> c.getParentId() != null && c.getParentId().equals(node.getId()))
				.sorted((x, y) -> Integer.compare(y.getOrgType(), x.getOrgType())).collect(Collectors.toList());

		if (!childs.isEmpty()) {
			for (OrganizItem f : childs) {

				TreeNode n = new TreeNode();
				n.setId(f.getOrgId());
				n.setText(f.getDisplayName());
				n.setState(TreeNode.STATE_OPEN);
				n.setIconCls("icon-folder");
				node.getChildren().add(n);

				appendChildFolders(n, items);
			}
		}
	}

}
