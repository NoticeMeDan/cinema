package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.CustomerEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.lang.reflect.Array;

public interface CustomerDao {
    @SqlUpdate("INSERT INTO customers(mobile_number, orders_fk) VALUES (:phone_number, :id)")
    void saveCustomer(@Bind("phone_number") int phone_number, @Bind("id")  id);

    @SqlUpdate("INSERT INTO customers(orders_fk) " +
               "VALUES (:id) " +
               "WHERE (:phone_number) = customers.mobile_number")
    void saveCustomerOrderId(@Bind("phone_number") int phone_number, @Bind("id") int orderId);

    @SqlQuery("SELECT customer " +
              "FROM customers " +
              "WHERE :phone_number = customers.mobile_number")
    @RegisterBeanMapper(CustomerEntity.class)
    CustomerEntity getCustomer(@Bind("phone_number") int phone_number);
}
