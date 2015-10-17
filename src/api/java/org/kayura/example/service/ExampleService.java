package org.kayura.example.service;

import java.util.Map;

import org.kayura.example.vo.OrderDetailVo;
import org.kayura.example.vo.OrderVo;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;

public interface ExampleService {

	PageList<OrderVo> findOrders(Map<String, Object> args, PageParams pageParams);

	OrderVo getOrderById(Integer orderId);

	Result<String> saveOrUpdateOrder(OrderVo order);

	Result<String> deleteOrderById(Integer orderId);

	PageList<OrderDetailVo> findOrderDetails(Map<String, Object> args, PageParams pageParams);

	OrderDetailVo getOrderDetailById(Integer orderDetailId);

	Result<String> saveOrUpdateOrderDetail(OrderDetailVo orderDetail);

	Result<String> deleteOrderDetailById(Integer orderDetailId);
	
}
