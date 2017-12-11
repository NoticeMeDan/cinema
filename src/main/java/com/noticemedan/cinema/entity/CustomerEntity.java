package com.noticemedan.cinema.entity;

public class CustomerEntity {
    private String phoneNumber;

    public CustomerEntity(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
