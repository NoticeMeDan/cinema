package com.noticemedan.cinema.view;

import com.noticemedan.cinema.controller.UIController;
import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.CustomerService;
import com.noticemedan.cinema.service.OrderService;
import com.noticemedan.cinema.service.SeatService;
import com.noticemedan.cinema.service.ShowService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TopBarView extends StateView {
    // Buttons
    @FXML private Button newOrderButton;
    @FXML private TextField customerId;
    @FXML private Label showCurrentUser;
    @FXML private TableView<OrderView> tableView;

    SeatService seatService = new SeatService();
    ShowService showService = new ShowService();
    OrderService orderService = new OrderService();
    CustomerService customerService = new CustomerService();

    public void findCustomer(){
        String phoneNumber = this.customerId.getText();

        // Remove activeorder if changing customer
        setActiveOrder(0);

        try {
            if( !phoneNumber.isEmpty() ) {
                // Check if phonenumber is valid
                if (!phoneNumber.matches("^\\d{8}$")) {
                    throw new IllegalArgumentException("Phone number is not valid.");
                }
                List<OrderEntity> orders = new ArrayList<>(this.orderService.getOrders(phoneNumber));

                if (orders.size() == 0) {
                    this.customerService.saveCustomer(phoneNumber);
                    this.tableView.setItems(FXCollections.emptyObservableList());
                } else {
                    this.showOrders(orders);
                }

                setValidCustomer(true);

                LeftBarView leftBarView = new LeftBarView();
                leftBarView.updateSelectionByMovie(null);
            } else {
                throw new IllegalArgumentException("Please type a phone number.");
            }
        } catch (Exception e) {
            UIController uiController = new UIController();
            setValidCustomer(false);
            this.handleNewOrderButtonState();
            uiController.alertBox(
                e.getMessage(),
                "Bad input",
                "Huh?"
            );
        }
        showCurrentUser.setText(this.customerId.getText());
    }

    private void showOrders(List<OrderEntity> orders) {
        List<OrderView> showOrders = getShowOrders();
        // Remove already existing orders in table
        showOrders.clear();

        orders.forEach(order -> {
            List<SeatEntity> orderSeats = this.seatService.getOrderSeats(order.getId());
            if (orderSeats.size() != 0) {
                ShowEntity show = this.showService.getSeatShow(orderSeats.get(0).getShowId());
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
                showOrders.add(new OrderView(roomNumber.toString(), movieTitle, startTime, date, orderId));
                setShowOrders(showOrders);
            }
        });
        //Data for TableView
        ObservableList<OrderView> list = FXCollections.observableArrayList(showOrders);

        tableView.setItems(list);
        tableView.setOnMousePressed(event -> {
            OrderView orderView = tableView.getSelectionModel().getSelectedItem();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate date = LocalDate.parse(orderView.getDate(), formatter);
            List<SeatEntity> seats = this.seatService.getOrderSeats(orderView.getOrderId());

            setPickDateData(date);
            setPickMovieData(orderView.getMovie());
            setPickTimeData(orderView.getTime());
            setActiveOrder(orderView.getOrderId());

            LeftBarView leftBarView = new LeftBarView();
            UIController uiController = new UIController();
            List<String> bookedSeats = getBookedSeats();
            List<String> chosenSeats = getChosenSeats();
            leftBarView.updateSelectionByMovie(null);

            seats.forEach(seat -> {
                bookedSeats.remove(seat.getSeatNumber());
                chosenSeats.add(seat.getSeatNumber());
            });

            setBookedSeats(bookedSeats);
            setChosenSeats(chosenSeats);

            // Enable delete button
            uiController.handleSaveOrderButtonState();

            // Set active order to selected order
            setActiveOrder(orderView.getOrderId());
            SeatAreaView seatAreaView = new SeatAreaView();
            seatAreaView.drawSeats();
        });
    }

    public void handleNewOrderButtonState() {
        this.newOrderButton.setDisable(!isValidCustomer());
    }

    public void createOrder() {
        List<String> chosenSeats = getChosenSeats();
        LeftBarView leftBarView = new LeftBarView();
        UIController uiController = new UIController();

        // Remove chosen seats and update view
        chosenSeats.clear();
        setChosenSeats(chosenSeats);
        leftBarView.updateSelectionByMovie(null);

        String phoneNumber = this.customerId.getText();

        setActiveOrder(this.orderService.saveOrder(phoneNumber));
        uiController.handleSaveOrderButtonState();

        System.out.println(getActiveOrder());
    }

    public void init() {
        // Empty tableView placeholder text
        Label placeholderText = new Label();
        placeholderText.setText("Enter a phone number or create an order");
        this.tableView.setPlaceholder(placeholderText);

    }
}
