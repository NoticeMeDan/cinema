package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.CustomerEntity;

public class CustomerService extends BaseService {
    public CustomerService() {
        super();
    }

    public void saveCustomer(String phoneNumber) {
        CustomerEntity customerEntity = new CustomerEntity(phoneNumber);
        customerDao.saveCustomer(customerEntity.getPhoneNumber());
    }
}
