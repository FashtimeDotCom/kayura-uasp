/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.po.DictDefine;
import org.kayura.uasp.po.DictItem;
import org.kayura.uasp.service.DictService;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.StringUtils;
import org.kayura.web.BaseController;
import org.kayura.web.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * GeneralController
 *
 * @author liangxia@live.com
 */
@Controller
@RequestMapping("/gm")
public class GeneralController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(GeneralController.class);

	@Autowired
	private DictService dictService;

	public GeneralController() {
		this.setViewRootPath("views/general/");
	}

	/**
	 * 数据词典管理浏览页.
	 */
	@RequestMapping(value = "/dict", method = RequestMethod.GET)
	public ModelAndView dictList() {

		return this.view("dictlist");
	}

	@RequestMapping(value = "/dicts", method = RequestMethod.POST)
	public void loadDicts(Map<String, Object> map) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				List<TreeNode> nodes = new ArrayList<TreeNode>();

				String tenantId = getLoginUser().getTenantId();
				Result<List<DictDefine>> r = dictService.loadDictDefinces(tenantId);
				if (r.isSucceed()) {
					List<DictDefine> list = r.getData();

					List<String> categories = list.stream().map(c -> c.getCatetory()).distinct()
							.collect(Collectors.toList());

					TreeNode root = new TreeNode();
					root.setId("ROOT");
					root.setText("数据词典");

					for (String c : categories) {

						TreeNode n = new TreeNode();
						n.setId("CATEGORY");
						n.setText(c);

						List<DictDefine> l2 = list.stream().filter(f -> f.getCatetory().equals(c))
								.collect(Collectors.toList());

						for (DictDefine l : l2) {

							TreeNode d = new TreeNode();
							d.setId(l.getId());
							d.setText(l.getName());
							
							n.getChildren().add(d);
						}
						
						root.getChildren().add(n);
					}

					nodes.add(root);
				}

				ps.add("items", nodes);
			}
		});
	}

	@RequestMapping(value = "/dict/load", method = RequestMethod.POST)
	public void loadDictItems(HttpServletRequest req, Map<String, Object> map, String dictId) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				PageParams pp = ui.getPageParams(req);

				if (dictId.equals("ROOT") || dictId.equals("CATEGORY")) {

					PageList<DictItem> list = new PageList<DictItem>(pp);
					ps.setCode(Result.SUCCEED);
					ps.add("items", ui.genPageData(list));
				} else {

					String tenantId = getLoginUser().getTenantId();
					Result<PageList<DictItem>> r = dictService.loadDictItems(dictId, tenantId, pp);
					ps.setCode(r.getCode());
					if (r.isSucceed()) {
						ps.add("items", ui.genPageData(r.getData()));
					} else {
						ps.addMessage(r.getMessage());
					}
				}
			}
		});
	}

	@RequestMapping(value = "/dict/new", method = RequestMethod.GET)
	public ModelAndView editDict(String pid, String id) {

		ModelAndView mv;

		Result<DictDefine> r = dictService.getDictDefineById(id);
		if (r.isSucceed()) {

			mv = this.view("dictedit");

			DictItem di = new DictItem();
			di.setDictId(id);
			di.setDictName(r.getData().getName());

			Boolean treeType = r.getData().getDataType() == DictDefine.DATATYPE_TREE;
			if (treeType && !StringUtils.isEmpty(pid)) {

				Result<DictItem> item = dictService.getDictItemsById(pid);
				if (item.isSucceed()) {
					di.setParentId(pid);
					di.setParentName(item.getData().getDictName());
				}
			}

			mv.addObject("treeType", treeType);
			mv.addObject("model", di);
		} else {

			mv = this.errorPage(r.getMessage(), "");
		}

		return mv;
	}

	@RequestMapping(value = "/dict/edit", method = RequestMethod.GET)
	public ModelAndView getDictItem(String id) {

		ModelAndView mv;

		Result<DictItem> item = dictService.getDictItemsById(id);
		if (item.isSucceed()) {

			mv = this.view("dictedit");
			mv.addObject("model", item.getData());
		} else {

			mv = this.errorPage(item.getMessage(), "");
		}

		return mv;
	}

	@RequestMapping(value = "/dict/save", method = RequestMethod.POST)
	public void saveDictItem(Map<String, Object> map, String name, DictItem item) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				LoginUser user = getLoginUser();
				GeneralResult r;
				if (StringUtils.isEmpty(item.getId())) {

					item.setId(KeyUtils.newId());
					item.setIsFixed(user.hasAnyRole(LoginUser.ROLE_ROOT));

					r = dictService.createDictItem(item);
				} else {

					r = dictService.updateDictItem(item);
				}
				ps.setResult(r);
			}
		});
	}

	@RequestMapping(value = "/dict/del", method = RequestMethod.POST)
	public void saveDictItem(Map<String, Object> map, String id) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				Result<DictItem> item = dictService.getDictItemsById(id);
				if (item.isSucceed()) {

					if (item.getData().getIsFixed() && !getLoginUser().hasAnyRole(LoginUser.ROLE_ROOT)) {

						ps.setFalied("保留的数据 不允许被删除。");
						return;
					}

					GeneralResult r = dictService.deleteDictItem(id);
					ps.setResult(r);
				}
			}
		});
	}
}
