package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.entity.SeatEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface OrderDao {
    @SqlUpdate("")
    void saveOrder(@BindBean List<SeatEntity> seat);

    @SqlUpdate("")
    @RegisterBeanMapper(OrderEntity.class)
    List<SeatEntity> getSeats();

}
