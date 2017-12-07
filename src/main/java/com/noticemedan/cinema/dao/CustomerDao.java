package com.noticemedan.cinema.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CustomerDao {
    @SqlUpdate("INSERT INTO user(phone_number, orders) VALUES (:phone_number) ")
    void saveCustomer(@Bind("phone_number") String phone_number);
}