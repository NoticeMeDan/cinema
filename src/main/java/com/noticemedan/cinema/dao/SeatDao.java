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
}
