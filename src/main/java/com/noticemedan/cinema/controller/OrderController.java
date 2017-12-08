package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.service.OrderService;

import java.util.List;

public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = new OrderService();
    }

    public void getCostumerOrder(String phoneNumber, int orderId) {
        List<OrderEntity> costumerOrders = orderService.getCostumerOrders(orderId, phoneNumber);
    }
}
