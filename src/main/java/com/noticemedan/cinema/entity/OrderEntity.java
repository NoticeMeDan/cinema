package com.noticemedan.cinema.entity;

public class OrderEntity {
    private int id;
    private String customerId;

    public OrderEntity() {}

    public OrderEntity(int id, String customerId) {
        setId(id);
        setCustomerId(customerId);
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
