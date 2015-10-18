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