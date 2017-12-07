package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.OrderEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface OrderDao {
    @SqlUpdate("")
    void reserveSeats();

    @SqlUpdate("")
    @RegisterBeanMapper(OrderEntity.class)
    List<Seats> getSeats();

}
