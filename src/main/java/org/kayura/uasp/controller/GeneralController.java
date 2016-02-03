/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.po.DictDefine;
import org.kayura.uasp.po.DictItem;
import org.kayura.uasp.service.DictService;
import org.kayura.utils.KeyUtils;
import org.kayura.web.BaseController;
import org.kayura.web.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String dictList() {

		return this.viewResult("dictlist");
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

	@RequestMapping(value = "/dictItems", method = RequestMethod.POST)
	public void loadDictItems(HttpServletRequest req, Map<String, Object> map, String dictId) {

		postExecute(map, new PostAction() {
			
			@Override
			public void invoke(PostResult ps) {
				
				PageParams pageParams = ui.getPageParams(req);
				PageList<DictItem> items = dictService.loadDictItems(dictId, null, pageParams);
				ps.add("items", ui.createData(items));
			}
		});
	}

	@RequestMapping(value = "/newdict", method = RequestMethod.GET)
	public String editDict(HttpServletRequest req, Map<String, Object> map, String pid, String id){

		DictItem di = new DictItem();
		di.setDictId(id);
		di.setParentId(pid);
		
		map.put("model", di);
		return this.viewResult("dictedit");
	}
	
}
