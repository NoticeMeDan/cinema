package com.noticemedan.cinema.entity;

import java.util.List;

public class OrderEntity {
    private int id;
    private List<SeatEntity> seats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SeatEntity> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatEntity> seats) {
        this.seats = seats;
    }
}
