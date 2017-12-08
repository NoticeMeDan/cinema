package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.CustomerEntity;
import java.util.List;

public class CustomerService extends BaseService {
    public CustomerService() {
        super();
    }

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
    }
}
