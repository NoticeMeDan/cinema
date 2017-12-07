package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.CustomerEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CustomerDao {
    @SqlUpdate("INSERT INTO customers(phone_number, orders) VALUES (:phone_number)")
    void saveCustomer(@Bind("phone_number") String phone_number);

    @SqlQuery("SELECT customer FROM customers WHERE :phone_number = phone_number")
    @RegisterBeanMapper(CustomerEntity.class)
    CustomerEntity getCustomer(@Bind("phone_number") String phone_number);
}
