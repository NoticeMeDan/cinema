package com.noticemedan.cinema.controller;

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
import java.util.*;

public class UIController implements Initializable {
    //Customer ID and Login
    //TopPane
    @FXML private DatePicker pickDate;
    @FXML private TextField customerID;
    @FXML private Label showCurrentUser;

    //TODO better comment: Pick info
    @FXML private ComboBox pickTime;
    @FXML private ComboBox pickMovie;
    @FXML private Label info;

    //TableView
    @FXML private TableView<OrderView> tableView;

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
            this.alertBox(
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
        info.setText("'" + pickMovie.getValue().toString() + "' ["
                    + pickTime.getValue().toString() + "] - "
                    + pickDate.getValue().toString());
    }
    public void newOrder() {

    }
    //Things to initialize
    //Data for Movies and Time
    private ObservableList<String> movies = FXCollections.observableArrayList("John Hitler", "John Hitler 2");
    private ObservableList<String> time = FXCollections.observableArrayList("8:00", "12:00");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initialize movies with default value
        pickMovie.setItems(movies);
        pickMovie.getSelectionModel().selectFirst();
        //Initialize time with default value
        pickTime.setItems(time);
        pickTime.getSelectionModel().selectFirst();
        //Initialize date with default value
        pickDate.setValue(LocalDate.now());
        getInfo();
    }

    public static void alertBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
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
