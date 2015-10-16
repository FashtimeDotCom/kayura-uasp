package org.kayura.example.dao;

import org.kayura.example.po.Shipper;

public interface ShipperMapper {
	
    int deleteByKey(Integer id);

    int insert(Shipper record);

    int insertSelective(Shipper record);

    Shipper selectByKey(Integer id);

    int updateBySelective(Shipper record);

    int updateByKey(Shipper record);
}