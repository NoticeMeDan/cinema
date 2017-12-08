package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.CustomerEntity;
import com.noticemedan.cinema.entity.OrderEntity;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;

import static java.sql.DriverManager;


public class CustomerService extends BaseService {
    private final CustomerEntity customerEntity;

    public CustomerService() {
        super();
        this.customerEntity = new CustomerEntity();
    }

    public void createUser (String phoneNumber) {
        customerEntity.setPhoneNumber(phoneNumber);
        customerDao.saveCustomer(88888888, id);
    }

    public CustomerEntity getCustomer(String phoneNumber) {
        return customerDao.getCustomer(Integer.parseInt(phoneNumber));
    }

    public void saveCustomerOrderId(String phoneNumber, int orderId) {
        customerEntity.setOrderId(orderId);
        customerDao.saveCustomerOrderId(Integer.parseInt(phoneNumber), orderId);
    }
}
