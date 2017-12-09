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

    public List<OrderEntity> getOrders(String phoneNumber) {
        return orderService.getOrders(phoneNumber);
    }

    public void deleteOrder(int orderId) {
        orderService.deleteOrder(orderId);
    }

    public void saveOrder(String phoneNumber) {
        orderService.saveOrder(phoneNumber);
    }
}
