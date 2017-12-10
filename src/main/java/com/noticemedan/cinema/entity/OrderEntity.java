package com.noticemedan.cinema.entity;

public class OrderEntity {
    private int id;
    private String customerId;

    public OrderEntity(int id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public OrderEntity(int id) {
        this.id = id;
    }

    public OrderEntity(String customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
