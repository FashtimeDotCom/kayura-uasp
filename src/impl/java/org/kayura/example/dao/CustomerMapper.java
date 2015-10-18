package org.kayura.example.dao;

import org.kayura.core.BaseDao;
import org.kayura.example.po.Customer;

public interface CustomerMapper extends BaseDao {
	
    int deleteByKey(String id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByKey(String id);

    int updateBySelective(Customer record);

    int updateByKey(Customer record);
}