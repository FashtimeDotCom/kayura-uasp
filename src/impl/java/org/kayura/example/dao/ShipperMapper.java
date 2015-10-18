package org.kayura.example.dao;

import org.kayura.core.BaseDao;
import org.kayura.example.po.Shipper;

public interface ShipperMapper extends BaseDao {
	
    int deleteByKey(Integer id);

    int insert(Shipper record);

    int insertSelective(Shipper record);

    Shipper selectByKey(Integer id);

    int updateBySelective(Shipper record);

    int updateByKey(Shipper record);
}