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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class UIController implements Initializable {
    //Customer ID and Login
    //TopPane
    @FXML private DatePicker pickDate;
    @FXML private TextField customerId;
    @FXML private Label showCurrentUser;

    //TODO better comment: Pick info
    @FXML private ComboBox<String> pickTime;
    @FXML private ComboBox<String> pickMovie;
    @FXML private Label info;

    //TableView
    @FXML private TableView<OrderView> tableView;

    // Seats
    @FXML private Pane seat_group;

    // Data for input fields (or whatever you call it)
    private ObservableList<String> movies;
    private ObservableList<String> times;

    public void findCustomer(){
        OrderController orderController = new OrderController();
        String phoneNumber = customerId.getText();

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
        showCurrentUser.setText(customerId.getText());
    }

    private void showOrders(List<OrderEntity> orders) {
        ShowController showController = new ShowController();
        SeatController seatController = new SeatController();
        List<OrderView> showOrders = new ArrayList<>();

        orders.forEach(order -> {
            List<SeatEntity> orderSeats = seatController.getOrderSeats(order.getId());
            Integer seatAmount = orderSeats.size();
            ShowEntity show = showController.getSeatShow(orderSeats.get(0).getShowId());
            LocalDateTime timeslot_start = show.getTimeslot().getStartTime().toLocalDateTime();

            // Leftpad starTime with 0 if minute is less than 10
            String startTime = String.format("%s:%02d",
                    timeslot_start.getHour(),
                    timeslot_start.getMinute()
                );
            String date = String.format("%s/%s/%s",
                   timeslot_start.getDayOfMonth(),
                   timeslot_start.getMonthValue(),
                   timeslot_start.getYear()
                );
            String movieTitle = show.getMovie().getName();
            Integer roomNumber = show.getRoom().getId();
            showOrders.add(new OrderView(roomNumber.toString(), movieTitle, startTime, date));
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

        this.drawSeats();

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

    private void drawSeats() {
        List<Rectangle> seats = new ArrayList<>();
        int row = 10;
        int col = 10;

        int distanceX = 10;
        int distanceY = 10;

        for(int x = 1; x <= row; x++){
            for (int y = 1; y <= col; y++){
                Rectangle rectangle = new Rectangle(distanceX, distanceY,15, 15);
                rectangle.setStroke(Color.GREEN);
                rectangle.setFill(Color.GREEN.deriveColor(1, 1, 1, 0.7));
                //rectangle.relocate(10, 10);
                distanceX += 20;


                // ID: seat:seatNumber (counted from left to right, up to down)
                rectangle.setId("seat:" + (x*row - (col - y)));

                rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    // Get id
                    String eventSourceId = e.getPickResult().getIntersectedNode().getId();
                    // Get seat position from id
                    String seatNumber = eventSourceId.split(":")[1];
                    System.out.println("Seat number: " + seatNumber);
                });

                seats.add(rectangle);
            }
            distanceX = 10;
            distanceY += 20;
        }

        this.seat_group.getChildren().addAll(seats);
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
