package com.noticemedan.cinema.view;

import com.noticemedan.cinema.service.BaseService;
import javafx.beans.property.SimpleStringProperty;

public class OrderView {
    private final SimpleStringProperty room = new SimpleStringProperty("");
    private final SimpleStringProperty movie = new SimpleStringProperty("");
    private final SimpleStringProperty time = new SimpleStringProperty("");
    private final SimpleStringProperty date = new SimpleStringProperty("");
    private int orderId = 0;

    public OrderView() {
        this("", "", "", "", 0);
    }

    public OrderView(String room, String movie, String time, String date, int orderId) {
        setRoom(room);
        setMovie(movie);
        setTime(time);
        setDate(date);
        setOrderId(orderId);
    }

    public String getMovie() {
        return movie.get();
    }

    public void setMovie(String fMovie) {
        movie.set(fMovie);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String fTime) {
        time.set(fTime);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String fDate) {
        date.set(fDate);
    }

    public String getRoom() {
        return room.get();
    }

    public void setRoom(String fRoom) {
        room.set(fRoom);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}