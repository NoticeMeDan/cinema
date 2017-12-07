package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.service.OrderService;

import java.util.List;

public class OrderController {
    private OrderService orderService = new OrderService();
    
    public void newOrder(List<SeatEntity> seats) {
        orderService.newOrder(seats);
    }
}
