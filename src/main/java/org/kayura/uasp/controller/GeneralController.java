/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
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

					TreeNode root = new TreeNode();
					root.setId(KeyUtils.newId());
					root.setText("数据词典");

					for (DictDefine d : list) {

						TreeNode n = new TreeNode();
						n.setId(d.getId());
						n.setText(d.getName());

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

				PageParams pageParams = ui.getPageParams(req);
				Result<PageList<DictItem>> r = dictService.loadDictItems(dictId, null, pageParams);
				ps.setCode(r.getCode());
				if (r.isSucceed()) {
					ps.add("items", ui.genPageData(r.getData()));
				} else {
					ps.addMessage(r.getMessage());
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
			di.setIsFixed(false);

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

	@RequestMapping(value = "/dict/save", method = RequestMethod.POST)
	public void saveDictItem(Map<String, Object> map, DictItem item) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				item.setId(KeyUtils.newId());
				item.setIsFixed(false);
				if (StringUtils.isEmpty(item.getParentId())) {
					item.setParentId(null);
				}
				GeneralResult r = dictService.createDictItem(item);
				ps.setResult(r);
			}
		});
	}
}
