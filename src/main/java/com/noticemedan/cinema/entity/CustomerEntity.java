package com.noticemedan.cinema.entity;

public class CustomerEntity {
    private String phoneNumber;
<<<<<<< HEAD
    private int orderId;

    public CustomerEntity() {}

    public CustomerEntity(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public CustomerEntity(String phoneNumber, int orderId) {
        setPhoneNumber(phoneNumber);
        setOrderId(orderId);
    }
=======
    private List<Integer> orderIds;
>>>>>>> e7a6447670f5243c6a31e4ac28dceb28f0919292

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

<<<<<<< HEAD
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
=======
    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderId(int orderId) {
        this.orderIds.add(orderId);
>>>>>>> e7a6447670f5243c6a31e4ac28dceb28f0919292
    }
}
