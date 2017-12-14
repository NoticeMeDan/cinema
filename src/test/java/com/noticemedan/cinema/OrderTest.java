package com.noticemedan.cinema;

import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.OrderService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.ArrayList;
import java.util.List;

public class OrderTest extends BaseService {
    private  OrderService orderService;

    @BeforeMethod
    public void setUp() throws Exception {
        this.orderService = new OrderService();
    }

    @Test
    public void testOrderCreation() throws Exception {
        int orderId = this.orderService.saveOrder("12345678");
        Assert.assertFalse(orderId == 0);
        
        this.orderService.deleteOrder(orderId);
    }

    @Test
    public void testGetOrders() throws Exception {
        List<OrderEntity> orders = this.orderService.getOrders("12345678");
        Assert.assertNotEquals(orders, null);
    }

    @Test
    public void testGetOrderForNotExistingCustomer() throws Exception {
        List<OrderEntity> orders = new ArrayList<>();
        try {
            orders = this.orderService.getOrders("123456789");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(orders.size() == 0);
    }
}
