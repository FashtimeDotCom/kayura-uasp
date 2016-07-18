package org.kayura.uasp.dao;

import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.uasp.po.MockOrder;

public interface MockOrderMapper {

	PageList<MockOrder> selectMockOrders(Map<String, Object> args, PageBounds pageBounds);

	void insertMockOrder(MockOrder mockOrder);

	void updateMockOrder(MockOrder mockOrder);

	void deleteMockOrder(String orderId);
}
