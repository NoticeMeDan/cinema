package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.CustomerEntity;
import java.util.List;

public class CustomerService extends BaseService {
    public CustomerService() {
        super();
        this.customerEntity = new CustomerEntity();
    }

    public List<Integer> getCustomerOrderIds(String phoneNumber) {
        CustomerEntity customerEntity = new CustomerEntity(phoneNumber);

        return customerDao.getCustomerOrderIds(customerEntity.getPhoneNumber());
    }

    public void saveCustomer(String phoneNumber) {
        CustomerEntity customerEntity = new CustomerEntity(phoneNumber);
        customerDao.saveCustomer(customerEntity.getPhoneNumber());
    }
}
