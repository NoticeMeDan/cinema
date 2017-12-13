package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.OrderEntity;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface OrderDao {
    @SqlUpdate("INSERT INTO orders(customer_fk) " +
               "VALUES (:customer_id)")
    @GetGeneratedKeys
    int saveOrder(@Bind("customer_id") String customer_id);

    @SqlQuery("SELECT * " +
              "FROM orders " +
              "WHERE :customer_id = orders.customer_fk")
    @RegisterConstructorMapper(OrderEntity.class)
    List<OrderEntity> getOrders(@Bind("customer_id") String customer_id);

    @SqlUpdate("DELETE FROM orders " +
               "WHERE :order_id = orders.id")
    void deleteOrder(@Bind("order_id") int order_id);
}
