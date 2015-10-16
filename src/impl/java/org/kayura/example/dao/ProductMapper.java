package org.kayura.example.dao;

import org.kayura.example.po.Product;

public interface ProductMapper {
	
    int deleteByKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByKey(Integer id);

    int updateBySelective(Product record);

    int updateByKey(Product record);
}