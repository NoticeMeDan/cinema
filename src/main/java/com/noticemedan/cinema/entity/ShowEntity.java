package com.noticemedan.cinema.entity;

import java.util.List;

public class ShowEntity {
    private int id;
    private List<RoomEntity> rooms;
    private List<MovieEntity> movies;
    private List<TimeSlotEntity> timeslots;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    public List<TimeSlotEntity> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List<TimeSlotEntity> timeslots) {
        this.timeslots = timeslots;
    }
}
