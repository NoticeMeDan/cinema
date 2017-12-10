package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.service.OrderService;

import java.util.List;
import java.util.Optional;

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

    public void saveOrder(String phoneNumber) {
        orderService.saveOrder(phoneNumber);
    }

}
