package com.noticemedan.cinema.entity;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class SeatEntity {
    private int id;
    private int showId;
    private int orderId;
    private int seatNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatEntity(@ColumnName("ID") int id,
                      @ColumnName("show_fk") int showId,
                      @ColumnName("order_fk") int orderId,
                      @ColumnName("seat_number") int seatNumber) {
        this.id = id;
        this.showId = showId;
        this.orderId = orderId;
        this.seatNumber = seatNumber;
    }
}
