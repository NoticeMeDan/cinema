package com.noticemedan.cinema;

import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.CustomerService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerTest extends BaseService {
    private CustomerService customerService;

    @BeforeMethod
    public void setUp() throws Exception {
        this.customerService = new CustomerService();
    }

    @Test
    public void testCustomerCreation() throws Exception {
        // Should be some kind of MockData which gets deleted
        // this.customerService.saveCustomer("12345679");
    }
}