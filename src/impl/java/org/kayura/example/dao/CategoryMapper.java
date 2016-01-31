/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.example.dao;

import org.kayura.core.BaseDao;
import org.kayura.example.po.Category;

public interface CategoryMapper extends BaseDao {

	int insert(Category record);

	int insertSelective(Category record);

	Category selectByKey(Integer id);

	int updateBySelective(Category record);

	int updateByWithBLOBs(Category record);

	int updateByKey(Category record);

	int deleteByKey(Integer id);
}