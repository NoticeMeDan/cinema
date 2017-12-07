package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.CustomerEntity;

public class CustomerService extends BaseService {
    private final CustomerEntity customerEntity = new CustomerEntity();

    public CustomerService() {
        super();
    }

    public void createUser (String phoneNumber) {
        customerEntity.setPhoneNumber(phoneNumber);
        customerDao.saveCustomer(customerEntity.getPhoneNumber());
    }
}
