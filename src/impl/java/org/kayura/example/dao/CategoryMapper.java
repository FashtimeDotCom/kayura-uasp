package org.kayura.example.dao;

import org.kayura.example.po.Category;

public interface CategoryMapper {

	int insert(Category record);

	int insertSelective(Category record);

	Category selectByKey(Integer id);

	int updateBySelective(Category record);

	int updateByWithBLOBs(Category record);

	int updateByKey(Category record);

	int deleteByKey(Integer id);
}