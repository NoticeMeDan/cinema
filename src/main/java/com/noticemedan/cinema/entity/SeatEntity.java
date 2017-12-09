package com.noticemedan.cinema.entity;

public class SeatEntity {
    private int id;
    private int showId;
    private int orderId;
    private int seatNumber;

    public SeatEntity() {}

    public SeatEntity(int orderId, int seatNumber) {
        setOrderId(orderId);
        setSeatNumber(seatNumber);
    }

    public SeatEntity(int showId, int orderId, int seatNumber) {
        setShowId(showId);
        setOrderId(orderId);
        setSeatNumber(seatNumber);
    }

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
}
