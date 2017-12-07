package com.noticemedan.cinema.entity;

import java.util.List;

public class CustomerEntity {
    private String phoneNumber;
    private List<Integer> orderIds;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderId(int orderId) {
        this.orderIds.add(orderId);
    }
}
