package com.noticemedan.cinema.view;

import com.noticemedan.cinema.controller.UIController;
import com.noticemedan.cinema.entity.MovieEntity;
import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.ShowService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LeftBarView extends StateView {
    // Data for input fields (or whatever you call it)
    private ObservableList<String> movies;
    private ObservableList<String> times;
    @FXML private ComboBox<String> pickTime;
    @FXML private ComboBox<String> pickMovie;
    @FXML private DatePicker pickDate;
    ShowService showService = new ShowService();

    public void updateSelectionByDate() {
        // Date
        String date = this.pickDate.getValue().toString();

        // Get movies for date
        List<MovieEntity> movies = this.getMoviesByDate(date);

        // Get the titles
        List<String> movieTitles = movies.stream()
                .map(MovieEntity::getName)
                .distinct()
                .collect(Collectors.toList());

        this.movies = FXCollections.observableList(movieTitles);

        // Update date input fields
        this.updateSelectionByMovie(null);

        this.pickMovie.setItems(this.movies);
        // Mark first movie as selected
        this.pickMovie.getSelectionModel().selectFirst();
        setPickMovieData(this.pickMovie.getValue());
        setPickDateData(this.pickDate.getValue());
    }

    public void updateSelectionByMovie(ActionEvent actionEvent) {
        // Date
        String date = this.pickDate.getValue().toString();

        // Get shows for chosen date
        List<ShowEntity> shows = this.getShowsByDate(date);

        // Save shows
        setAvailableShows(shows);

        // Remove ActiveOrder when movie changes
        setActiveOrder(0);

        // Disable delete button
        UIController uiController = new UIController();
        uiController.handleSaveOrderButtonState();

        List<String> movieTimes;
        // If any movies for chosen date
        if (!this.movies.isEmpty()) {
            // Name of the first chosen movie
            String movieName = this.pickMovie.getValue();

            // Create datetime formatter, with the format of the show times
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            // Get the play times of the first movie on the list
            movieTimes = shows.stream()
                    .filter(show -> show.getMovie().getName().equals(movieName))
                    .map(show -> show.getTimeslot().getStartTime().toLocalDateTime().format(formatter))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } else {
            movieTimes = Collections.emptyList();
        }

        // Only update timePicker, if the function was not called by the timepicker
        Node source;
        if (actionEvent != null) {
            source = (Node) actionEvent.getSource();

            if (!source.getId().equals("pickTime")) {
                this.times = FXCollections.observableList(movieTimes);
                this.pickTime.setItems(times);
                this.pickTime.getSelectionModel().selectFirst();
            }
        } else {
            this.times = FXCollections.observableList(movieTimes);
            this.pickTime.setItems(times);
            this.pickTime.getSelectionModel().selectFirst();
        }
        setPickMovieData(this.pickMovie.getValue());
        setPickDateData(this.pickDate.getValue());
        setPickTimeData(this.pickTime.getValue());

        // Update info
        SeatAreaView seatAreaView = new SeatAreaView();
        TopBarView topBarView = new TopBarView();
        seatAreaView.getInfo();
        topBarView.handleNewOrderButtonState();
        uiController.handleSaveOrderButtonState();

        // Update seats
        seatAreaView.updateSeats();
    }

    private List<MovieEntity> getMoviesByDate(String date) {
        List<ShowEntity> shows = this.getShowsByDate(date);
        if (!shows.isEmpty()) {
            return shows.stream()
                    .map(ShowEntity::getMovie)
                    .distinct()
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private List<ShowEntity> getShowsByDate(String date) {
        List<ShowEntity> shows = this.showService.getShowsByDate(date);
        if (!shows.isEmpty()) {
            return shows;
        } else {
            return Collections.emptyList();
        }
    }

    public void init() {
        LocalDate now = LocalDate.now();
        this.pickDate.setValue(now);
        this.pickDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.getDayOfYear() < now.getDayOfYear() && now.getYear() >= date.getYear());
            }
        });
        this.updateSelectionByDate();
    }


}
