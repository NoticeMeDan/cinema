package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.*;
import com.noticemedan.cinema.service.CustomerService;
import com.noticemedan.cinema.service.OrderService;
import com.noticemedan.cinema.service.SeatService;
import com.noticemedan.cinema.service.ShowService;
import com.noticemedan.cinema.view.*;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class UIController extends StateView implements Initializable {
    @FXML private Button saveOrderButton;
    @FXML private Button deleteOrderButton;

    // Data for input fields (or whatever you call it)
    private ObservableList<String> movies;
    private ObservableList<String> times;

    // Services
    private SeatService seatService = new SeatService();
    private ShowService showService = new ShowService();

    public void handleSaveOrderButtonState() {
        if (isValidCustomer() && getActiveOrder() != 0) {
            this.saveOrderButton.setDisable(false);
            this.deleteOrderButton.setDisable(false);
        } else {
            this.saveOrderButton.setDisable(true);
            this.deleteOrderButton.setDisable(true);
        }
    }

    //Get movie+time+date and display on info-label
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize seat arrays
        setChosenSeats(new ArrayList<>());
        setBookedSeats(new ArrayList<>());

        // Set date-picker to today
        LeftBarView leftBarView = new LeftBarView();
        TopBarView topBarView = new TopBarView();
        leftBarView.init();
        topBarView.init();
    }

    public void alertBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    public void deleteOrder() {
        OrderService orderService = new OrderService();

        seatService.deleteSeatBookings(getActiveOrder());
        orderService.deleteOrder(getActiveOrder());

        // Update order table
        TopBarView topBarView = new TopBarView();
        LeftBarView leftBarView = new LeftBarView();
        topBarView.findCustomer();

        // Update seats
        leftBarView.updateSelectionByMovie(null);
    }

    public void saveOrder(){
        System.out.println("Order saved");

        // If order already exists and this is just an edit
        if (this.seatService.doesOrderAlreadyExist(getActiveOrder())) {
            System.out.println("Updating selected seats");
            this.seatService.deleteSeatBookings(getActiveOrder());
        }

        getChosenSeats().forEach((String seat) -> {
            this.seatService.bookSeat(seat, getSelectedShow().get().getId(), getActiveOrder());
        });

        // Update order table
        TopBarView topBarView = new TopBarView();
        SeatAreaView seatAreaView = new SeatAreaView();
        topBarView.findCustomer();

        // Update seats
        seatAreaView.updateSeats();
    }

}
