/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.example.service.impl;

import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;

import org.kayura.example.service.ExampleService;
import org.kayura.example.vo.OrderDetailVo;
import org.kayura.example.vo.OrderVo;
import org.kayura.example.vo.convert.OrderConvert;
import org.kayura.example.vo.convert.OrderDetailConvert;
import org.kayura.example.dao.OrderDetailMapper;
import org.kayura.example.dao.OrderMapper;
import org.kayura.example.po.Order;
import org.kayura.example.po.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liangxia@live.com
 */
@Service
public class ExampleServiceImpl implements ExampleService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Override
	public PageList<OrderVo> findOrders(Map<String, Object> args, PageParams pageParams) {

		PageList<Order> orders = orderMapper.findByMap(args, new PageBounds(pageParams));
		return OrderConvert.toVos(orders);
	}

	@Override
	public OrderVo getOrderById(Integer orderId) {

		Order entity = orderMapper.getById(orderId);
		return OrderConvert.toVo(entity);
	}

	@Override
	public Result<String> saveOrUpdateOrder(OrderVo order) {

		Result<String> result = new Result<String>("订单保存成功。");

		try {
			Order entity = OrderConvert.toEntity(order);
			if (order.getId() == 0) {
				orderMapper.insert(entity);
			} else {
				orderMapper.updateByKey(entity);
			}
		} catch (Exception e) {
			result.setError("订单保存失败。原因：%s", e.getMessage());
		}

		return result;
	}

	@Override
	public Result<String> deleteOrderById(Integer orderId) {

		Result<String> result = new Result<String>("订单保存成功。");
		try {
			orderMapper.deleteByKey(orderId);
		} catch (Exception e) {
			result.setError("订单删除失败。原因：%s", e.getMessage());
		}
		return result;
	}

	@Override
	public PageList<OrderDetailVo> findOrderDetails(Map<String, Object> args, PageParams pageParams) {

		PageList<OrderDetail> details = orderDetailMapper.findByMap(args, new PageBounds(pageParams));
		return OrderDetailConvert.toVos(details);
	}

	@Override
	public OrderDetailVo getOrderDetailById(Integer detailId) {

		OrderDetail entity = orderDetailMapper.getById(detailId);
		return OrderDetailConvert.toVo(entity);
	}

	@Override
	public Result<String> saveOrUpdateOrderDetail(OrderDetailVo orderDetail) {
		
		Result<String> result = new Result<String>("订单保存成功。");

		try {
			OrderDetail entity = OrderDetailConvert.toEntity(orderDetail);
			if (orderDetail.getId() == 0) {
				orderDetailMapper.insert(entity);
			} else {
				orderDetailMapper.updateByKey(entity);
			}
		} catch (Exception e) {
			result.setError("订单保存失败。原因：%s", e.getMessage());
		}

		return result;
	}

	@Override
	public Result<String> deleteOrderDetailById(Integer detailId) {
		
		Result<String> result = new Result<String>("订单清单项保存成功。");
		try {
			orderDetailMapper.deleteByKey(detailId);
		} catch (Exception e) {
			result.setError("订单清单项删除失败。原因：%s", e.getMessage());
		}
		return result;
	}

}
