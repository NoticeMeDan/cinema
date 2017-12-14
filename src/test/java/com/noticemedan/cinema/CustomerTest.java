package com.noticemedan.cinema;

import com.noticemedan.cinema.controller.CustomerController;
import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.CustomerService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerTest extends BaseService {
    private CustomerController customerController;

    @BeforeMethod
    public void setUp() throws Exception {
        this.customerController = new CustomerController();
    }

    @Test
    public void testCustomerCreation() throws Exception {
        // Should be some kind of MockData which gets deleted
        // this.customerController.saveCustomer("12345679");
    }
}