package com.noticemedan.cinema;

import com.noticemedan.cinema.controller.OrderController;
import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.service.BaseService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class OrderTest extends BaseService {
    private  OrderController orderController;

    @BeforeMethod
    public void setUp() throws Exception {
        this.orderController = new OrderController();
    }

    @Test
    public void testOrderCreation() throws Exception {
        int orderId = this.orderController.saveOrder("12345678");
        this.orderController.deleteOrder(orderId);
    }

    @Test
    public void testGetOrders() throws Exception {
        List<OrderEntity> orders = this.orderController.getOrders("12345678");
    }
}
