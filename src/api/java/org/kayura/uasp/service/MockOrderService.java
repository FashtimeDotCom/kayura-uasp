package org.kayura.uasp.service;

import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.uasp.po.MockOrder;

public interface MockOrderService {

	PageList<MockOrder> selectMockOrders(String teanantId, String keyword, PageParams pageParams);

	void createMockOrder(MockOrder mockOrder);

	void updateMockOrder(MockOrder mockOrder);

	void deleteMockOrder(String orderId);

}
