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
        OrderEntity orderEntity = new OrderEntity(customerId);
        orderDao.saveOrder(orderEntity.getCustomerId());
    }

    public void deleteOrder(int orderId) {
        OrderEntity orderEntity = new OrderEntity(orderId);
        orderDao.deleteOrder(orderEntity.getId());
    }

    public Optional<List<OrderEntity>> getOrders(String customerId) {
        OrderEntity orderEntity = new OrderEntity(customerId);
        return orderDao.getOrders(orderEntity.getCustomerId());
    }
}
