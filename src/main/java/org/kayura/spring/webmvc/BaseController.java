/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.spring.webmvc;

import org.kayura.logging.Log;
import org.kayura.logging.LogFactory;
import org.kayura.type.PageParams;
import org.kayura.utils.PathUtils;
import org.kayura.type.PageList;
import org.kayura.web.UISupport;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author liangxia@live.com
 */
public class BaseController {

	private static final Log logger = LogFactory.getLog(BaseController.class);

	private ObjectMapper objectMapper = new ObjectMapper();
	private UISupport webuiSupport;
	private String viewRootPath;

	protected void setViewRootPath(String viewRootPath) {
		this.viewRootPath = viewRootPath;
	}

	/***
	 * 用于从提供请求信息中获取分页信息.
	 * 
	 * @param req Http提供请求信息.
	 * @return 分页信息对象.
	 */
	public PageParams getPageBounds(HttpServletRequest req) {
		return webuiSupport.getPageBounds(req);
	}

	/***
	 * 向请求结果集添加数据.
	 * <p>
	 * totalCount: 表示总记录数.
	 * <p>
	 * data: 表示查询结果集.
	 * 
	 * @param model
	 * @param pageList
	 */
	public void putData(Model model, PageList<?> pageList) {
		webuiSupport.putData(model, pageList);
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
	public void putData(Map<String, Object> map, PageList<?> pageList) {
		webuiSupport.putData(map, pageList);
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

	/**
	 * 将对象转换成 json 格式的字符串.
	 * 
	 * @param object 用于转换的对象.
	 * @return 返回 json 格式的字符串.
	 */
	public String jsonResult(Object object) {
		String json = "";
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			json = "{'result':{'type':'error',message:'数据转换成 Json 时发生异常.','attr':{}}}";
		}
		return json;
	}

	/**
	 * 用于代理执行一个方法，并且返回统一格式的结果值.
	 * 
	 * @param map 一个 Key,Value类型的集合,它由SpringMvc创建.
	 * @param executeAction 代理的执行方法,可以建立它的匿名方法.
	 */
	public void execute(Map<String, Object> map, Action executeAction) {

		PostResult postResult = new PostResult();

		try {
			executeAction.invoke(postResult);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			postResult.setError(e.getMessage());
		}

		map.clear();
		map.put("type", postResult.getType());
		map.put("message", postResult.getMessage());
		map.put("attr", postResult.getAttr());
	}

}