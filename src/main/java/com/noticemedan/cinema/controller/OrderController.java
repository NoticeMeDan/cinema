package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.service.OrderService;

import java.util.List;

public class OrderController {
    private OrderService orderService;

    OrderController() {
        this.orderService = new OrderService();
    }

    List<OrderEntity> getOrders(String phoneNumber) {
        return orderService.getOrders(phoneNumber);
    }

    public void deleteOrder(int orderId) {
        orderService.deleteOrder(orderId);
    }

    public int saveOrder(String phoneNumber) {
        return orderService.saveOrder(phoneNumber);
    }

}
