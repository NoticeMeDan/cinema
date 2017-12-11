package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.OrderEntity;

import java.util.List;

public class OrderService extends BaseService {
    public OrderService() {
        super();
    }

    public void saveOrder(String customerId) {
        orderDao.saveOrder(customerId);
    }

    public void deleteOrder(int orderId) {
        orderDao.deleteOrder(orderId);
    }

    public List<OrderEntity> getOrders(String customerId) {
        return orderDao.getOrders(customerId);
    }
}
