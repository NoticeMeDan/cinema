package com.noticemedan.cinema.entity;

import java.util.List;

public class CustomerEntity {
    private String phoneNumber;
    private List<OrderEntity> orders;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List getOrders() {
        return orders;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }
}
