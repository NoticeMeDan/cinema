package com.noticemedan.cinema.entity;

public class SeatEntity {
    private int id;
    private ShowEntity show;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShowEntity getShow() {
        return show;
    }

    public void setShow(ShowEntity show) {
        this.show = show;
    }
}
