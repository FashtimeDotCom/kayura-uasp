package org.kayura.example.dao;

import org.kayura.example.po.OrderDetail;

public interface OrderDetailMapper {
	
    int deleteByKey(Integer id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByKey(Integer id);

    int updateBySelective(OrderDetail record);

    int updateByKey(OrderDetail record);
}