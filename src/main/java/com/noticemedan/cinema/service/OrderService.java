package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.OrderEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
