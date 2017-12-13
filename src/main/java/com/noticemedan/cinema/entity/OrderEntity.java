package com.noticemedan.cinema.entity;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class OrderEntity {
    private int id;
    private String customerId;

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

    public OrderEntity(@ColumnName("id") int id, @ColumnName("customer_fk") String customerId) {
        this.id = id;
        this.customerId = customerId;
    }
}
