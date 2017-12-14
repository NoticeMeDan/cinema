package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.SeatEntity;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface SeatDao {
    @SqlUpdate("INSERT INTO seats(show_fk, order_fk, seat_number) " +
               "VALUES (:show_id, :order_id, :seat_number)")
    void bookSeat(@Bind("seat_number") String seat_number,
                  @Bind("show_id") int show_id,
                  @Bind("order_id") int order_id);

    @SqlUpdate("DELETE FROM seats WHERE :order_id = order_fk")
    void deleteSeatBooking(@Bind("order_id") int order_id);

    @SqlQuery("SELECT * " +
              "FROM seats " +
              "WHERE :order_id = seats.order_fk")
    @RegisterConstructorMapper(SeatEntity.class)
    List<SeatEntity> getOrderSeats(@Bind("order_id") int order_id);

    @SqlQuery("SELECT * " +
              "FROM seats " +
              "WHERE show_fk = :show_id")
    @RegisterConstructorMapper(SeatEntity.class)
    List<SeatEntity> getBookedSeatsByShowId(@Bind("show_id") int show_id);
}
