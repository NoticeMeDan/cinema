package com.noticemedan.cinema.entity;

public class OrderEntity {
    private int id;
    private List<Seats> seats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Seats> getSeats() {
        return seats;
    }

    public void setSeats(List<Seats> seats) {
        this.seats = seats;
    }
}
