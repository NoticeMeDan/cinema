package com.noticemedan.cinema.service;

import com.noticemedan.cinema.dao.CustomerDao;
import com.noticemedan.cinema.entity.CustomerEntity;

public class CustomerService extends BaseService {
    private final CustomerDao customerDao;
    private final CustomerEntity customerEntity;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
        this.customerEntity = new CustomerEntity();
    }

    public void createUser (String phoneNumber) {
        customerEntity.setPhoneNumber(phoneNumber);
        customerDao.saveCustomer(customerEntity.getPhoneNumber());
    }
}
