package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.CustomerEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.lang.reflect.Array;
import java.util.List;

public interface CustomerDao {
    @SqlUpdate("INSERT INTO customers(phone_number, orders_fk) " +
               "VALUES (:phone_number, :orderId) ")
    void saveCustomerOrder(@Bind("phone_number") String phone_number, @Bind("orderId") int orderId);

    @SqlQuery("SELECT orders_fk " +
              "FROM customers " +
              "WHERE :phone_number = customers.phone_number")
    @RegisterBeanMapper(CustomerEntity.class)
    List<Integer> getCustomerOrderIds(@Bind("phone_number") String phone_number);

    @SqlQuery("SELECT orders_fk " +
              "FROM customers " +
              "WHERE :phone_number = customers.phone_number " +
              "AND :orderId = customers.orders_fk")
    int getCustomerOrderId(@Bind("phone_number") String phone_number, @Bind("orderId") int orderId);

    @SqlUpdate("DELETE FROM customers " +
               "WHERE :phone_number = customers.phone_number " +
               "AND :orderId = customers.orders_fk")
    void deleteCustomerOrder(@Bind("phone_number") String phone_number, @Bind("orderId") int orderId);
}
