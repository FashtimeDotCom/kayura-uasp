/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.web.ui;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kayura.type.PageList;
import org.kayura.type.PageParams;

/**
 * @author liangxia@live.com
 */
public interface UISupport {

	/**
	 * 用于从提供请求信息中获取分页信息.
	 * 
	 * @param req Http提供请求信息.
	 * @return 分页信息对象.
	 */
	PageParams getPageParams(HttpServletRequest req);

	/**
	 * 向请求结果集添加数据.
	 * <p>totalCount: 表示总记录数.
	 * <p>data: 表示查询结果集.
	 * 
	 * @param model
	 * @param pageList
	 */
	Map<String, Object> putData(Map<String, Object> model, PageList<?> pageList);
	
	/**
	 * 创建一个分页的数据包.
	 * 
	 * @param pageList
	 * @return
	 */
	Map<String, Object> putData(PageList<?> pageList);
}
