package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.service.CustomerService;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
}
