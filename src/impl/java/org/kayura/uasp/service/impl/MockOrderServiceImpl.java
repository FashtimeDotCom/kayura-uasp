package org.kayura.uasp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.kayura.utils.DateUtils;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.StringUtils;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.uasp.po.MockOrder;
import org.kayura.uasp.dao.MockOrderMapper;
import org.kayura.uasp.service.MockOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockOrderServiceImpl implements MockOrderService {

	@Autowired
	private MockOrderMapper mockOrderMapper;

	@Override
	public PageList<MockOrder> selectMockOrders(String tenantId, String keyword, PageParams pageParams) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isNotEmpty(tenantId)) {
			args.put("tenantId", tenantId);
		}

		if (StringUtils.isNotEmpty(keyword)) {
			args.put("keyword", keyword);
		}

		return mockOrderMapper.selectMockOrders(args, new PageBounds(pageParams));
	}

	@Override
	public void createMockOrder(MockOrder mockOrder) {

		if (StringUtils.isEmpty(mockOrder.getOrderId())) {
			mockOrder.setOrderId(KeyUtils.newId());
		}

		if (mockOrder.getCreateTime() == null) {
			mockOrder.setCreateTime(DateUtils.now());
		}

		mockOrder.setUpdateTime(mockOrder.getCreateTime());

		mockOrderMapper.insertMockOrder(mockOrder);
	}

	@Override
	public void updateMockOrder(MockOrder mockOrder) {

		mockOrder.setUpdateTime(DateUtils.now());
		mockOrderMapper.updateMockOrder(mockOrder);
	}

	@Override
	public void deleteMockOrder(String orderId) {
		mockOrderMapper.deleteMockOrder(orderId);
	}

}
