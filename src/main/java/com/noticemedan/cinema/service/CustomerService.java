package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.CustomerEntity;
import com.noticemedan.cinema.entity.OrderEntity;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;

import static java.sql.DriverManager;


import java.util.ArrayList;
import java.util.List;

public class CustomerService extends BaseService {
    private final CustomerEntity customerEntity;

    public CustomerService() {
        super();
        this.customerEntity = new CustomerEntity();
    }

    public void createUser (String phoneNumber, int orderId) {
        customerEntity.setPhoneNumber(phoneNumber);
        customerDao.saveCustomer(phoneNumber, orderId);
    }

    public CustomerEntity getCustomer(String phoneNumber) {
        return customerDao.getCustomer(phoneNumber);
    }

    public void saveCustomerOrderId(String phoneNumber, int orderId) {
        customerEntity.setOrderId(orderId);
        customerDao.saveCustomerOrderId(phoneNumber, orderId);
    }

    public CustomerEntity getCustomer() {
        return customerDao.getCustomer("88888888");
    }

    public void testCustomers() {
        List customers = new ArrayList<>();
        customers.add("88888888");
        customers.add("98888888");
        customers.add("18888888");

        customers.forEach(customer -> createUser(customer.toString()));
    }

    public void testSaveOrders() {

    }

    public void saveCustomerOrderId() {
        //customerEntity.setOrderIds();
        //customerDao.saveCustomerOrderId("88888888", customerEntity);
    }
}
