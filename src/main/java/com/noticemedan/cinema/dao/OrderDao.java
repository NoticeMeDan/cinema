package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.OrderEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    @SqlUpdate("INSERT INTO orders(customer_fk) " +
               "VALUES (:customer_id)")
    void saveOrder(@Bind("customer_id") String customer_id);

    @SqlQuery("SELECT * " +
              "FROM orders " +
              "WHERE :customer_id = orders.customer_fk")
    @RegisterRowMapper(OrderEntity.class)
    Optional<List<OrderEntity>> getOrders(@Bind("customer_id") String customer_id);

    @SqlUpdate("DELETE FROM orders " +
               "WHERE :order_id = orders.id")
    void deleteOrder(@Bind("order_id") int order_id);
}
