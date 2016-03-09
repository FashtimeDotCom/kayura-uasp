/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.utils.PathUtils;
import org.kayura.web.ui.UISupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liangxia@live.com
 */
public class BaseController {

	private static final Log logger = LogFactory.getLog(BaseController.class);

	@Autowired
	protected UISupport ui;

	protected ObjectMapper objectMapper = new ObjectMapper();
	protected String viewRootPath;

	protected void setViewRootPath(String viewRootPath) {
		this.viewRootPath = viewRootPath;
	}

	/***
	 * 用于从提供请求信息中获取分页信息.
	 * 
	 * @param req Http提供请求信息.
	 * @return 分页信息对象.
	 */
	public PageParams getPageParams(HttpServletRequest req) {
		return ui.getPageParams(req);
	}

	/**
	 * 向请求结果集添加数据.
	 * <p>
	 * totalCount: 表示总记录数.
	 * <p>
	 * data: 表示查询结果集.
	 * 
	 * @param model
	 * @param pageList
	 */
	public void putData(Map<String, Object> model, PageList<?> pageList) {
		ui.putData(model, pageList);
	}

	/**
	 * 创建一个视图页的路径.
	 * 
	 * @param viewName 视图文件名.
	 * @return 返回 viewRootPath下的视图名的.
	 */
	public String viewResult(String viewName) {

		return PathUtils.merge(viewRootPath, viewName);
	}

	public ModelAndView errorPage(Exception ex) {

		return errorPage(ex.getMessage(), ex.toString());
	}

	public ModelAndView errorPage(String message, String details) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("details", details);

		return errorPage(map);
	}

	public ModelAndView errorPage(Map<String, Object> map) {

		return new ModelAndView("shared/error", map);
	}

	public ModelAndView view(String viewName) {

		return new ModelAndView(PathUtils.merge(viewRootPath, viewName));
	}

	public ModelAndView view(String viewName, Map<String, Object> model) {

		ModelAndView mv = view(viewName);
		mv.addAllObjects(model);

		return mv;
	}

	/**
	 * 用于代理执行一个方法，并且返回统一格式的结果值.
	 * 
	 * @param map 一个 Key,Value类型的集合,它由SpringMvc创建.
	 * @param postAction 代理的执行方法,可以建立它的匿名方法.
	 */
	public void postExecute(Model model, PostAction postAction) {

		Map<String, Object> args = model.asMap();
		postExecute(args, postAction);
	}

	/**
	 * 用于代理执行一个方法，并且返回统一格式的结果值.
	 * 
	 * @param map 一个 Key,Value类型的集合,它由SpringMvc创建.
	 * @param postAction 代理的执行方法,可以建立它的匿名方法.
	 */
	public void postExecute(Map<String, Object> model, PostAction postAction) {

		PostResult postResult = new PostResult();

		try {
			postAction.invoke(postResult);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			postResult.setError(e.getMessage());
		}

		model.clear();
		model.put("type", postResult.getType());
		model.put("message", postResult.getMessage());
		model.put("data", postResult.getData());
	}

	public String json(Object data) {
		String s = null;
		try {
			s = objectMapper.writeValueAsString(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return s;
	}

	public LoginUser getLoginUser() {
		return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}