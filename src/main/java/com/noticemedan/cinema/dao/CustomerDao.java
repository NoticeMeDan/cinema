package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.CustomerEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CustomerDao {
    @SqlUpdate("INSERT INTO customers(phone_number) VALUES (:phone_number)")
    void saveCustomer(@Bind("phone_number") String phone_number);

    @SqlUpdate("INSERT INTO customers(orders_fk) " +
               "VALUES (:id) " +
               "WHERE (:phone_number) = customers.phone_number")
    void saveCustomerOrderId(@Bind("phone_number") String phone_number, @Bind("id") int orderId);

    @SqlQuery("SELECT customer " +
              "FROM customers " +
              "WHERE :phone_number = customers.phone_number")
    @RegisterBeanMapper(CustomerEntity.class)
    CustomerEntity getCustomer(@Bind("phone_number") String phone_number);
}
