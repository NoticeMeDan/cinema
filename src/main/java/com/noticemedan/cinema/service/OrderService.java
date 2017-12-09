package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.entity.SeatEntity;

import java.util.List;

public class OrderService extends BaseService {
    public OrderService() {
        super();
    }

    public void saveCustomerOrder (int orderId, String customerId) {
        OrderEntity orderEntity = new OrderEntity(orderId, customerId);
        orderDao.saveOrder(orderEntity.getId(), orderEntity.getCustomerId());
    }

    public void deleteCustomerOrder(int orderId, String customerId) {
        OrderEntity orderEntity = new OrderEntity(orderId, customerId);
        orderDao.deleteCustomerOrder(orderEntity.getId(), orderEntity.getCustomerId());
    }

    public List<OrderEntity> getCostumerOrders(int orderId, String customerId) {
        OrderEntity orderEntity = new OrderEntity(orderId, customerId);
        return orderDao.getCustomerOrders(orderEntity.getCustomerId());
    }
}
