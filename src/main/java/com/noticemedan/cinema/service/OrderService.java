package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.entity.SeatEntity;

import java.util.List;

public class OrderService extends BaseService {
    private final OrderEntity orderEntity = new OrderEntity();

    public OrderService() {
        super();
    }

    public void newOrder(List seats) {
        orderEntity.setSeats(seats);
        this.orderDao.createOrder(orderEntity.getSeats());
    }

    public void editOrder() {
        List<SeatEntity> seats = orderEntity.getSeats();
    }
}
