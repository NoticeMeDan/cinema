package com.noticemedan.cinema.entity;

public class CustomerEntity {
    private String phoneNumber;
    private int orderId;

    public CustomerEntity() {}

    public CustomerEntity(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public CustomerEntity(String phoneNumber, int orderId) {
        setPhoneNumber(phoneNumber);
        setOrderId(orderId);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
