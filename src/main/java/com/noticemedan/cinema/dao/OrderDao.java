package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.entity.SeatEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface OrderDao {
    @SqlUpdate("INSERT INTO orders(id, customer_fk) " +
<<<<<<< HEAD
               "VALUES (:order_id, :customer_id)")
    void saveOrder(@Bind("order_id") int order_id,
                   @Bind("customer_id") String customer_id);

    @SqlQuery("SELECT * " +
              "FROM orders " +
              "WHERE :customer_id = orders.customer_fk")
    @RegisterBeanMapper(OrderEntity.class)
    List<OrderEntity> getCustomerOrders(@Bind("customer_id") String customer_id);

    @SqlUpdate("DELETE FROM orders " +
               "WHERE :customer_id = orders.customer_fk " +
               "AND :order_id = orders.id")
    void deleteCustomerOrder(@Bind("order_id") int order_id,
                             @Bind("customer_id") String customer_id);
=======
               "VALUES (:orderID, :customerId)")
    void saveOrder(@Bind("orderId") int orderId, @Bind("customerId") String customerId);

    @SqlQuery("SELECT * " +
              "FROM orders " +
              "WHERE :customerId = orders.customer_fk")
    @RegisterBeanMapper(OrderEntity.class)
    List<OrderEntity> getCustomerOrders(@Bind("customerId") String customerId);

    @SqlUpdate("DELETE FROM orders " +
               "WHERE :customerId = orders.customer_fk " +
               "AND :orderId = orders.id")
    void deleteCustomerOrder(@Bind("orderId") int orderId, @Bind("customerId") String customerId);
>>>>>>> SHIT!
}
