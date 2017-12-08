package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.service.CustomerService;

import java.util.List;

public class CustomerController {
    private CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerService();
    }

    public void saveCustomerOrder(String phoneNumber) {
        customerService.saveCustomer(phoneNumber);
    }
}
