package com.noticemedan.cinema.entity;

public class ShowEntity {
    private int id;
    private RoomEntity room;
    private MovieEntity movie;
    private TimeSlotEntity timeslot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public TimeSlotEntity getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlotEntity timeslot) {
        this.timeslot = timeslot;
    }

    public ShowEntity(int id, RoomEntity room, MovieEntity movie, TimeSlotEntity timeslot) {
        this.id = id;
        this.room = room;
        this.movie = movie;
        this.timeslot = timeslot;
    }
}
