/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.web.ui;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kayura.type.OrderBy.Direction;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;

/**
 * @author liangxia@live.com
 */
public class EasyUISupport implements UISupport {

	@Override
	public PageParams getPageParams(HttpServletRequest req) {

		// page=1&rows=10&sort=itemid&order=desc

		int pageIndex = Integer.getInteger(req.getParameter("page"), 0);
		int pageSize = Integer.getInteger(req.getParameter("rows"), 0);

		String sortField = req.getParameter("sort");
		String sortOrder = req.getParameter("order");

		PageParams pageParams = new PageParams();
		pageParams.setPage(pageIndex + 1);
		pageParams.setLimit(pageSize);
		pageParams.setContainsTotalCount(true);

		if (sortField != null && sortField != "" && sortOrder != null && sortOrder != "") {
			Direction direction = sortOrder.equals("asc") ? Direction.ASC : Direction.DESC;
			pageParams.setOrderBy(sortField, direction);
		}

		return pageParams;
	}

	@Override
	public void putData(Map<String, Object> map, PageList<?> pageList) {

		// {"total":"28","rows":[{}] }
		
		map.put("totalCount", pageList.getTotalCount());
		map.put("data", pageList);
	}

}
