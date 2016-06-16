package org.kayura.uasp.dao;

import java.util.List;
import java.util.Map;

import org.kayura.core.BaseDao;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.uasp.po.BizForm;

public interface BpmMapper extends BaseDao {

	PageList<BizForm> findBizForms(Map<String, Object> args, PageBounds pageBounds);

	List<BizForm> loadBizForms(String tenantId);

	BizForm getBizFormById(Map<String, Object> args);

	void insertBizForm(BizForm bizForm);

	void updateBizForm(Map<String, Object> args);

	void deleteBizForms(Map<String, Object> args);

}
