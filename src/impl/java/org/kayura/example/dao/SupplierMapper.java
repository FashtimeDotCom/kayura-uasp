/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.example.dao;

import org.kayura.core.BaseDao;
import org.kayura.example.po.Supplier;

public interface SupplierMapper extends BaseDao {

	int deleteByKey(Integer id);

	int insert(Supplier record);

	int insertSelective(Supplier record);

	Supplier selectByKey(Integer id);

	int updateBySelective(Supplier record);

	int updateByKey(Supplier record);
}