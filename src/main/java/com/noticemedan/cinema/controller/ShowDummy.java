package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.service.BaseService;
import javafx.beans.property.SimpleStringProperty;

public class ShowDummy extends BaseService {
    private final SimpleStringProperty movie = new SimpleStringProperty("");
    private final SimpleStringProperty time = new SimpleStringProperty("");
    private final SimpleStringProperty date = new SimpleStringProperty("");

    public ShowDummy() {
        this("", "", "");
    }

    public ShowDummy(String movie, String time, String date) {
        setMovie(movie);
        setTime(time);
        setDate(date);
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
}