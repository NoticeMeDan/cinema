package com.noticemedan.cinema.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SeatDao {
    @SqlUpdate("INSERT INTO seats(show_fk, order_fk, seat_number) " +
               "VALUES (:show_id, :order_id, :seat_number)")
    void bookSeat(@Bind("seat_number") int seat_number,
                  @Bind("show_id") int show_id,
                  @Bind("order_id") int order_id);

    @SqlQuery("DELETE FROM seats " +
              "WHERE :seat_number = seat_number " +
              "AND :order_id = order_fk")
    void deleteSeatBooking(@Bind("seat_number") int seat_number,
                           @Bind("order_id") int order_id);
}
