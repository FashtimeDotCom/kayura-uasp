/**
 * Copyright 2015-2016 the original author or authors.
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
import org.kayura.uasp.po.User;
import org.kayura.uasp.service.DictService;
import org.kayura.uasp.service.UserService;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.StringUtils;
import org.kayura.web.BaseController;
import org.kayura.tags.easyui.types.TreeNode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liangxia@live.com
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private DictService dictService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AdminController() {
		this.setViewRootPath("views/admin/");
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "user/list", method = RequestMethod.GET)
	public String userList() {

		return viewResult("user/list");
	}

	@RequestMapping(value = { "user/find", "/file/sharer/find" }, method = RequestMethod.POST)
	public void userFind(HttpServletRequest req, Map<String, Object> map, String keyword, String status) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				PageParams pageParams = ui.getPageParams(req);

				String tenantId = getLoginUser().getTenantId();
				Integer[] intStatus = StringUtils.toInteger(status);

				Result<PageList<User>> r = userService.findUsers(tenantId, keyword, intStatus, pageParams);
				ps.setCode(r.getCode());
				if (r.isSucceed()) {
					ps.setData(ui.genPageData(r.getData()));
				} else {
					ps.addMessage(r.getMessage());
				}
			}
		});
	}

	@RequestMapping(value = "user/new", method = RequestMethod.GET)
	public String userNew(HttpServletRequest req, Map<String, Object> map, String id) {

		User user = new User();
		user.setRoles("USER");
		user.setIsEnabled(true);
		user.setIsLocked(false);
		map.put("model", user);

		return viewResult("user/edit");
	}

	@RequestMapping(value = "user/edit", method = RequestMethod.GET)
	public String userEdit(HttpServletRequest req, Map<String, Object> map, String id) {

		User user = userService.getUserById(id);
		user.setPassword("");
		map.put("model", user);

		return viewResult("user/edit");
	}

	@RequestMapping(value = "user/save", method = RequestMethod.POST)
	public void userSave(Map<String, Object> map, User user) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				if (StringUtils.isEmpty(user.getUserId())) {

					String tenantId = getLoginUser().getTenantId();
					String salt = KeyUtils.random();
					String hash = passwordEncoder.encodePassword(user.getPassword(), salt);

					user.setUserId(KeyUtils.newId());
					user.setTenantId(tenantId);
					user.setSalt(salt);
					user.setPassword(hash);

					GeneralResult result = userService.createNewUser(user);
					ps.setResult(result);
				} else {

					GeneralResult result = userService.updateUserInfo(user);
					ps.setResult(result);
				}
			}
		});
	}

	/**
	 * 数据词典管理浏览页.
	 */
	@RequestMapping(value = "/dict/list", method = RequestMethod.GET)
	public ModelAndView dictList() {

		ModelAndView mv = this.view("dict/list");
		mv.addObject("ISROOT", this.getLoginUser().hasRoot());

		return mv;
	}

	@RequestMapping(value = "/dict/define", method = RequestMethod.POST)
	public void loadDicts(Map<String, Object> map) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				List<TreeNode> nodes = new ArrayList<TreeNode>();

				Result<List<DictDefine>> r = dictService.loadDictDefinces();
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

							n.addNode(d);
						}

						root.addNode(n);
					}

					nodes.add(root);
				}

				ps.setData(nodes);
			}
		});
	}

	@RequestMapping(value = "/dict/load", method = RequestMethod.POST)
	public void loadDictItems(HttpServletRequest req, Map<String, Object> map, String dictId, String parentId) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				PageParams pp = ui.getPageParams(req);
				if (dictId.equals("ROOT") || dictId.equals("CATEGORY")) {

					PageList<DictItem> list = new PageList<DictItem>(pp);
					ps.setCode(Result.SUCCEED);
					ps.setData(ui.genPageData(list));
				} else {

					String tenantId = getLoginUser().getTenantId();
					Result<PageList<DictItem>> r = dictService.loadDictItems(tenantId, dictId, parentId, pp);
					ps.setCode(r.getCode());
					if (r.isSucceed()) {
						ps.setData(ui.genPageData(r.getData()));
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

			mv = this.view("dict/edit");

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

			mv = this.view("dict/edit");
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
					item.setTenantId(user.getTenantId());
					item.setIsFixed(user.hasAnyRole(LoginUser.ROLE_ROOT));

					r = dictService.createDictItem(item);
					ps.setResult(r);
				} else {

					Result<DictItem> oi = dictService.getDictItemsById(item.getId());
					if (oi.isSucceed()) {

						if (oi.getData().getIsFixed() && !user.hasAnyRole(LoginUser.ROLE_ROOT)) {

							ps.setFalied("保留的数据 不允许被修改。");
						} else {

							r = dictService.updateDictItem(item);
							ps.setResult(r);
						}
					}
				}
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
