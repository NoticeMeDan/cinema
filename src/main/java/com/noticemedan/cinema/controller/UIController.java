package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.*;
import com.noticemedan.cinema.view.OrderView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    //Show information
    @FXML private ComboBox<String> pickTime;
    @FXML private ComboBox<String> pickMovie;
    @FXML private Label info;

    //TableView
    @FXML private TableView<OrderView> tableView;

    // Seats
    @FXML private Pane seat_group;

    // Buttons
    @FXML private Button newOrderButton;
    @FXML private Button saveOrderButton;
    @FXML private Button deleteOrderButton;

    // Data for input fields (or whatever you call it)
    private ObservableList<String> movies;
    private ObservableList<String> times;

    // UI State
    private List<String> chosenSeats;
    private List<String> bookedSeats;
    private List<ShowEntity> availableShows;
    private List<OrderView> showOrders = new ArrayList<>();
    private int ActiveOrder = 0;
    private boolean validCustomer = false;

    public void findCustomer(){
        OrderController orderController = new OrderController();
        CustomerController customerController = new CustomerController();
        String phoneNumber = this.customerId.getText();

        // Remove activeorder if changing customer
        this.ActiveOrder = 0;

        try {
            if( !phoneNumber.isEmpty() ) {
                // Check if phonenumber is valid
                if (!phoneNumber.matches("^\\d{8}$")) {
                    throw new IllegalArgumentException("Phone number is not valid.");
                }
                List<OrderEntity> orders = new ArrayList<>(orderController.getOrders(phoneNumber));

                if (orders.size() == 0) {
                    customerController.saveCustomerOrder(phoneNumber);
                    this.tableView.setItems(FXCollections.emptyObservableList());
                } else {
                    this.showOrders(orders);
                }

                this.validCustomer = true;

                this.updateSelectionByMovie(null);
            } else {
                throw new IllegalArgumentException("Please type a phone number.");
            }
        } catch (Exception e) {
            this.validCustomer = false;
            this.handleNewOrderButtonState();
            alertBox(
                e.getMessage(),
                "Bad input",
                "Huh?"
            );
        }
        showCurrentUser.setText(this.customerId.getText());
    }

    private void handleNewOrderButtonState() {
        if (this.validCustomer  &&
                this.pickDate.getValue() != null &&
                this.pickMovie.getValue() != null &&
                this.pickTime.getValue() != null) {

            this.newOrderButton.setDisable(false);
        } else {
            this.newOrderButton.setDisable(true);
        }
    }

    private void handleSaveOrderButtonState() {
        if (this.validCustomer && this.ActiveOrder != 0) {
            this.saveOrderButton.setDisable(false);
        } else {
            this.saveOrderButton.setDisable(true);
        }
    }

    private void showOrders(List<OrderEntity> orders) {
        // Remove already existing orders in table
        this.showOrders.clear();

        ShowController showController = new ShowController();
        SeatController seatController = new SeatController();

        orders.forEach(order -> {
            List<SeatEntity> orderSeats = seatController.getOrderSeats(order.getId());
            if (orderSeats.size() != 0) {
                ShowEntity show = showController.getSeatShow(orderSeats.get(0).getShowId());
                LocalDateTime timeslot_start = show.getTimeslot().getStartTime().toLocalDateTime();

                // Leftpad starTime with 0 if minute is less than 10
                String startTime = String.format("%02d:%02d",
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
                Integer orderId = order.getId();
                this.showOrders.add(new OrderView(roomNumber.toString(), movieTitle, startTime, date, orderId));
            }
        });
        //Data for TableView
        ObservableList<OrderView> list = FXCollections.observableArrayList(showOrders);

        tableView.setItems(list);
        tableView.setOnMousePressed(event -> {
            OrderView orderView = tableView.getSelectionModel().getSelectedItem();
            SeatController seatController1 = new SeatController();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate date = LocalDate.parse(orderView.getDate(), formatter);
            List<SeatEntity> seats = seatController1.getOrderSeats(orderView.getOrderId());

            this.pickDate.setValue(date);
            this.pickMovie.setValue(orderView.getMovie());
            this.pickTime.setValue(orderView.getTime());
            this.ActiveOrder = orderView.getOrderId();

            this.updateSelectionByMovie(null);
            
            seats.forEach(seat -> {
                this.bookedSeats.remove(seat.getSeatNumber());
                this.chosenSeats.add(seat.getSeatNumber());
            });

            // Enable delete button
            this.deleteOrderButton.setDisable(false);

            // Set active order to selected order
            this.ActiveOrder = orderView.getOrderId();

            // Enable save button
            this.handleSaveOrderButtonState();

            this.drawSeats();
        });
    }

    //Get movie+time+date and display on info-label
    private void getInfo(){
        if (this.pickMovie.getValue() != null &&
            this.pickDate.getValue() != null &&
            this.pickTime.getValue() != null) {
            info.setText("'" + this.pickMovie.getValue().toString() + "' ["
                    + this.pickTime.getValue().toString() + "] - "
                    + this.pickDate.getValue().toString());
        } else {
            info.setText("Please select a valid date, movie and time.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize seat arrays
        this.chosenSeats = new ArrayList<>();
        this.bookedSeats = new ArrayList<>();

        // Set date-picker to today
        LocalDate now = LocalDate.now();
        this.pickDate.setValue(now);
        this.pickDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            setDisable(empty || date.getDayOfYear() < now.getDayOfYear() && now.getYear() >= date.getYear());
            }
        });

        // Empty tableView placeholder text
        Label placeholderText = new Label();
        placeholderText.setText("No orders for a customer to show");
        this.tableView.setPlaceholder(placeholderText);
        
        // Do first update of selection UI
        this.updateSelectionByDate();
    }

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
    }

    public void updateSelectionByMovie(ActionEvent actionEvent) {
        // Date
        String date = this.pickDate.getValue().toString();

        // Get shows for chosen date
        List<ShowEntity> shows = this.getShowsByDate(date);

        // Save shows
        this.availableShows = shows;

        // Remove ActiveOrder when movie changes
        this.ActiveOrder = 0;

        // Disable delete button
        this.deleteOrderButton.setDisable(true);

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


        // Update info
        this.getInfo();

        this.handleNewOrderButtonState();
        this.handleSaveOrderButtonState();

        // Update seats
        this.updateSeats();
    }

    private void updateSeats() {
        // Get booked seats for currently selected show
        this.bookedSeats = this.getBookedSeats();

        // Remove chosen seats
        this.chosenSeats.clear();

        // Draw Seats
        this.drawSeats();
    }

    public void createOrder() {
        // Remove chosen seats and update view
        this.chosenSeats.clear();
        this.updateSelectionByMovie(null);

        OrderController orderController = new OrderController();
        String phoneNumber = this.customerId.getText();

        this.ActiveOrder = orderController.saveOrder(phoneNumber);
        this.handleSaveOrderButtonState();
        this.deleteOrderButton.setDisable(false);

        System.out.println(this.ActiveOrder);
    }

    public void deleteOrder() {
        OrderController orderController = new OrderController();
        SeatController seatController = new SeatController();
        seatController.deleteSeatBookings(this.ActiveOrder);
        orderController.deleteOrder(this.ActiveOrder);

        // Update order table
        this.findCustomer();

        // Update seats
        this.updateSelectionByMovie(null);
    }

    public void saveOrder(){
        OrderController orderController = new OrderController();
        SeatController seatController = new SeatController();

        System.out.println("Order saved");


        // If order already exists and this is just an edit
        if (seatController.doesOrderAlreadyExist(this.ActiveOrder)) {
            System.out.println("Updating selected seats");
            seatController.deleteSeatBookings(this.ActiveOrder);
        }

        this.chosenSeats.forEach((String seat) -> {
            seatController.bookSeat(seat, getSelectedShow().get().getId(), this.ActiveOrder);
        });

        // Update order table
        this.findCustomer();

        // Update seats
        this.updateSeats();
    }

    private void drawSeats() {
        // Get room of chosen show
        Optional<ShowEntity> show = this.getSelectedShow();
        RoomEntity room;

        if (show.isPresent()) {
            room = show.get().getRoom();
        } else {
            return;
        }

        // Store seats in array
        List<Rectangle> seats = new ArrayList<>();

        // Static distances
        int startDistanceX = 25;
        int startDistanceY = 10;
        int addDistanceX = 25;
        int addDistanceY = 40;
        int relocate = 30;

        // Remove current seats from display
        this.seat_group.getChildren().clear();

        for(int x = 1; x <= room.getRowAmount(); x++){
            for (int y = 1; y <= room.getColumnAmount(); y++){
                Rectangle rectangle = new Rectangle(startDistanceX, startDistanceY,25, 25);

                // ID: seat:seatNumber (counted from left to right, up to down)
                String seatNumber = x + ":" + y;
                rectangle.setId("seat-" + seatNumber);

                // Is seat booked?
                if (this.isSeatBooked(seatNumber)) {
                    // If yes, then make it red
                    rectangle.setStroke(Color.RED);
                    rectangle.setFill(Color.RED.deriveColor(1, 1, 1, 0.7));
                } else if (this.isSeatChosen(seatNumber)) {
                    // If yes, then make it yellow
                    rectangle.setStroke(Color.PURPLE);
                    rectangle.setFill(Color.PURPLE.deriveColor(1, 1, 1, 0.7));
                } else {
                    // If not, then make it green
                    rectangle.setStroke(Color.GREEN);
                    rectangle.setFill(Color.GREEN.deriveColor(1, 1, 1, 0.7));
                }

                //rectangle.relocate(10, 10);
                startDistanceX += relocate;

                rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    // Get seatId
                    String eventSourceId = e.getPickResult().getIntersectedNode().getId();

                    // Get seat position from id
                    String number = eventSourceId.split("-")[1];
                    System.out.println("Seat: " + number + " has been clicked.");

                    // Only select seats on an active order

                    if (this.ActiveOrder != 0) {
                        if (this.isSeatChosen(number)) {
                            this.removeSeat(number);
                        } else {
                            this.chooseSeat(number);
                        }

                        // Redraw seats
                        this.drawSeats();
                    } else {
                        alertBox(
                                "You can only edit seats on an Active Order",
                                "Info",
                                "Invalid input"
                        );
                    }
                });

                seats.add(rectangle);
            }
            startDistanceX = addDistanceX;
            startDistanceY += addDistanceY;
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

    private Optional<ShowEntity> getSelectedShow() {
        // Find show matching picked movie and date
        List<ShowEntity> chosenShowList = this.availableShows.stream()
                .filter(show -> {
                    String showTitle = show.getMovie().getName();
                    LocalDateTime showStartTime = show.getTimeslot().getStartTime().toLocalDateTime();
                    String pickedTitle = this.pickMovie.getValue();
                    String pickedTime = this.pickTime.getValue();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

                    return showTitle.equals(pickedTitle) &&
                            showStartTime.format(formatter).equals(pickedTime);
                })
                .collect(Collectors.toList());

        if (chosenShowList.size() > 0) {
            return Optional.of(chosenShowList.get(0));
        } else {
            return Optional.empty();
        }
    }

    private List<String> getBookedSeats() {
        SeatController seatController = new SeatController();
        Optional<ShowEntity> show = this.getSelectedShow();

        if (show.isPresent()) {
            List<SeatEntity> seats = seatController.getBookedSeatsByShowId(show.get().getId());
            return seats.stream()
                    .map(SeatEntity::getSeatNumber)
                    .distinct()
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private boolean isSeatBooked(String seatNumber) {
        return this.bookedSeats.contains(seatNumber);
    }

    private boolean isSeatChosen(String seatNumber) {
        return this.chosenSeats.contains(seatNumber);
    }

    private void chooseSeat(String seatNumber) {
        this.chosenSeats.add(seatNumber);
    }

    private void removeSeat(String seatNumber) {
        this.chosenSeats.remove(seatNumber);
    }
}
