package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.CustomerEntity;
import com.noticemedan.cinema.entity.OrderEntity;


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

    public CustomerEntity getCustomer(String phoneNumber) {
        return customerDao.getCustomer(phoneNumber);
    }

    public void saveCustomerOrderId(String phoneNumber, int orderId) {
        customerEntity.setOrderId(orderId);
        customerDao.saveCustomerOrderId(phoneNumber, orderId);
    }
}
