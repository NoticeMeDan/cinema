package com.noticemedan.cinema.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
    @FXML private TableView<ShowDummy> tableView;
    @FXML private TableColumn<ShowDummy, String> movieCol;
    @FXML private TableColumn<ShowDummy, String> timeCol;
    @FXML private TableColumn<ShowDummy, String> dateCol;

    //Get movie+time+date and display on info-label
    public void getInfo(){
        info.setText("'" + pickMovie.getValue().toString() + "' ["
                    + pickTime.getValue().toString() + "] - "
                    + pickDate.getValue().toString());
    }

    public void findCustomer(){
        OrderController orderController = new OrderController();
        String phoneNumber = customerID.getText();

        try {
            if( !phoneNumber.isEmpty() ) {
                orderController.findOrders(phoneNumber);
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

    //Things to initialize
    //Data for Movies and Time
    private ObservableList<String> movies = FXCollections.observableArrayList("John Hitler", "John Hitler 2");
    private ObservableList<String> time = FXCollections.observableArrayList("8:00", "12:00");
    //Data for TableView
    private ObservableList<ShowDummy> list = FXCollections.observableArrayList(
            new ShowDummy("John Hitler", "8:00", "8/4/2017"),
            new ShowDummy("John Hitler 2", "12:00", "8/5/2017"),
            new ShowDummy("John Hitler", "12:00", "9/4/2017"),
            new ShowDummy("Finding Nemo", "12:00", "9/4/2017")
    );

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
        //Initialize TableView with data
        tableView.setItems(list);
    }
    //TODO create new order
    public void newOrder(){
        //OrderController.createNewOrder();
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
