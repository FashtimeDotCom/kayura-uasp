package org.kayura.example.dao;

import java.util.Map;

import org.kayura.core.BaseDao;
import org.kayura.example.po.OrderDetail;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;

public interface OrderDetailMapper extends BaseDao {
	
    int deleteByKey(Integer id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail getById(Integer id);

    PageList<OrderDetail> findByMap(Map<String, Object> args, PageBounds pageBounds);

    int updateBySelective(OrderDetail record);

    int updateByKey(OrderDetail record);
}