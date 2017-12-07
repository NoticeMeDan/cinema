package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.service.CustomerService;

public class CustomerController {
    private CustomerService customerService = new CustomerService();

    public void createCustomer(String phoneNumber) {
        customerService.createUser(phoneNumber);
    }
}
