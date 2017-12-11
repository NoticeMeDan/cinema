package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.MovieEntity;
import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.view.OrderView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class UIController implements Initializable {
    //Customer ID and Login
    //TopPane
    @FXML private DatePicker pickDate;
    @FXML private TextField customerID;
    @FXML private Label showCurrentUser;

    //TODO better comment: Pick info
    @FXML private ComboBox<String> pickTime;
    @FXML private ComboBox<String> pickMovie;
    @FXML private Label info;

    //TableView
    @FXML private TableView<OrderView> tableView;

    // Data for input fields (or whatever you call it)
    private ObservableList<String> movies;
    private ObservableList<String> times;

    public void findCustomer(){
        OrderController orderController = new OrderController();
        String phoneNumber = customerID.getText();

        try {
            if( !phoneNumber.isEmpty() ) {
                this.showOrders(orderController.getOrders(phoneNumber));
            } else {
                throw new IllegalArgumentException("Remember to write a phone number or else I can't help you find the customer's orders");
            }
        } catch (Exception e) {
            alertBox(
                e.getMessage(),
                "No input",
                "Nothing?"
            );
        }
        showCurrentUser.setText(customerID.getText());
    }

    private void showOrders(List<OrderEntity> orders) {
        ShowController showController = new ShowController();
        SeatController seatController = new SeatController();
        List<OrderView> showOrders = new ArrayList<>();

        orders.forEach(order -> {
            List<SeatEntity> orderSeats = seatController.getOrderSeats(order.getId());
            Integer seatAmount = orderSeats.size();
            ShowEntity show = showController.getSeatShow(orderSeats.get(0).getShowId());
            Calendar cal = Calendar.getInstance();
            cal.setTime(show.getTimeslot().getStartTime());

            Integer startHour = cal.get(Calendar.HOUR_OF_DAY);
            Integer date = cal.get(Calendar.DATE);
            String movieTitle = show.getMovie().getName();
            Integer roomNumber = show.getRoom().getId();
            showOrders.add(new OrderView(roomNumber.toString(), movieTitle, date.toString(), startHour.toString()));
        });
        //Data for TableView
        ObservableList<OrderView> list = FXCollections.observableArrayList(showOrders);
        tableView.setItems(list);
    }
    //Get movie+time+date and display on info-label
    public void getInfo(){
        if (this.pickMovie.getValue() != null &&
            this.pickDate.getValue() != null &&
            this.pickTime.getValue() != null) {
            info.setText("'" + pickMovie.getValue().toString() + "' ["
                    + pickTime.getValue().toString() + "] - "
                    + pickDate.getValue().toString());
        } else {
            info.setText("Please select a valid date, movie and time.");
        }
    }
    public void newOrder() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set datepicker to today
        this.pickDate.setValue(LocalDate.now());

        // Do first update of selection UI
        this.updateSelectionByDate();

        getInfo();
    }

    public void updateSelectionByDate() {
        // Date
        String date = this.pickDate.getValue().toString();

        // Get movies for date
        List<MovieEntity> movies = this.getMoviesByDate(date);

        // Get the titles
        List<String> movieTitles = movies.stream()
                .map(MovieEntity::getName)
                .collect(Collectors.toList());

        this.movies = FXCollections.observableList(movieTitles);

        // Update date input fields
        this.updateSelectionByMovie();

        this.pickMovie.setItems(this.movies);
        // Mark first movie as selected
        this.pickMovie.getSelectionModel().selectFirst();
    }

    public void updateSelectionByMovie() {
        // Date
        String date = this.pickDate.getValue().toString();

        // Get shows for chosen date
        List<ShowEntity> shows = this.getShowsByDate(date);

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
                    .collect(Collectors.toList());
        } else {
            movieTimes = Collections.emptyList();
        }

        this.times = FXCollections.observableList(movieTimes);

        this.pickTime.setItems(times);
        this.pickTime.getSelectionModel().selectFirst();
    }

    static void alertBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
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
        ShowController showController = new ShowController();
        List<ShowEntity> shows = showController.getAvailableShowsByDate(date);
        if (!shows.isEmpty()) {
            return shows;
        } else {
            return Collections.emptyList();
        }
    }
    //TODO create save method

    //TODO update movie
    //Happens when you pick a date

    //TODO update time
    //Hapens when you pick a movie

    //TODO create delete method

    //Extra features
    //TODO create tutorial
}
