package com.noticemedan.cinema;

import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.OrderService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderTest extends BaseService {
    private OrderService orderService;

    @BeforeMethod
    public void setUp() throws Exception {
        this.orderService = new OrderService();
    }

    @Test
    public void testOrderCreation() throws Exception {
    }
}
