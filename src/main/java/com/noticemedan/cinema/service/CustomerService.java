package com.noticemedan.cinema.service;

public class CustomerService extends BaseService {
    public CustomerService() {
        super();
    }

    public void saveCustomer(String phoneNumber) {
        customerDao.saveCustomer(phoneNumber);
    }
}
