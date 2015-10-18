package org.kayura.example.dao;

import org.kayura.core.BaseDao;
import org.kayura.example.po.Employee;

public interface EmployeeMapper extends BaseDao {
	
    int deleteByKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByKey(Integer id);

    int updateBySelective(Employee record);

    int updateByWithBLOBs(Employee record);

    int updateByKey(Employee record);
}