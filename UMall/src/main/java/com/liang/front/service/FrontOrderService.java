package com.liang.front.service;

import com.liang.manager.dto.front.Order;
import com.liang.manager.dto.front.OrderInfo;

import java.util.List;

public interface FrontOrderService {

    List<Order> getOrderList(Long userId);

    Order getOrder(Long orderId);

    int cancelOrder(Long orderId);

    Long createOrder(OrderInfo orderInfo);

    int delOrder(Long orderId);
}
