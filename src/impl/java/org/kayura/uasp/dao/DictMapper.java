/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.dao;

import java.util.List;
import java.util.Map;

import org.kayura.core.BaseDao;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.uasp.po.DictDefine;
import org.kayura.uasp.po.DictItem;

/**
 * DictMapper
 *
 * @author liangxia@live.com
 */
public interface DictMapper extends BaseDao {

	List<DictDefine> loadDictDefinces(String tenantId);

	PageList<DictItem> findDictItems(Map<String, Object> args, PageBounds bounds);

	DictItem getDictItemsById(String itemId);

	void insertDictItem(DictItem dictItem);

	void updateDictItem(DictItem dictItem);

	void deleteDictItem(String itemId);

}
