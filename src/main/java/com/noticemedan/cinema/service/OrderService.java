package com.noticemedan.cinema.service;

import com.noticemedan.cinema.dao.OrderDao;
import com.noticemedan.cinema.entity.OrderEntity;

import java.util.List;

public class OrderService {
    private final OrderDao orderDao;
    private final OrderEntity orderEntity;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
        this.orderEntity = new OrderEntity();
    }

    public void newOrder(List seats) {
        orderEntity.setSeats(seats);
    }

    public void editOrder() {
        List<Seats> seats = orderEntity.getSeats();
    }
}
