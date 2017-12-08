package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.CustomerEntity;
<<<<<<< HEAD
import java.util.List;

public class CustomerService extends BaseService {
=======
import com.noticemedan.cinema.entity.OrderEntity;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;

import static java.sql.DriverManager;


public class CustomerService extends BaseService {
    private final CustomerEntity customerEntity;

>>>>>>> e7a6447670f5243c6a31e4ac28dceb28f0919292
    public CustomerService() {
        super();
        this.customerEntity = new CustomerEntity();
    }

<<<<<<< HEAD
    public List<Integer> getCustomerOrderIds(String phoneNumber) {
        CustomerEntity customerEntity = new CustomerEntity(phoneNumber);

        return customerDao.getCustomerOrderIds(customerEntity.getPhoneNumber());
    }

    public void saveCustomerOrder (String phoneNumber, int orderId) {
        CustomerEntity customerEntity = new CustomerEntity(phoneNumber, orderId);

        customerDao.saveCustomerOrder(customerEntity.getPhoneNumber(), customerEntity.getOrderId());
    }

    public void deleteCustomerOrder(String phoneNumber, int orderId) {
        CustomerEntity customerEntity = new CustomerEntity(phoneNumber, orderId);

        customerDao.deleteCustomerOrder(customerEntity.getPhoneNumber(), customerEntity.getOrderId());
=======
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
>>>>>>> e7a6447670f5243c6a31e4ac28dceb28f0919292
    }
}
