package com.noticemedan.cinema.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class UIController implements Initializable {
    //TopPane
    @FXML private DatePicker pickDate;
    @FXML private Button login;
    @FXML private TextField customerID;
    @FXML private Label showID;
    @FXML private Label ld;
    @FXML private Label info;
    @FXML private ComboBox pickTime;
    @FXML private ComboBox pickMovie;

    //Get movie+time+date and display on info-label
    public void getInfo(){
        info.setText("'" + pickMovie.getValue().toString() + "' ["
                    + pickTime.getValue().toString() + "] - "
                    + pickDate.getValue().toString());
    }
    public void login(){
        showID.setText(customerID.getText());
    }
    //Things to initialize
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
    //TODO TableView receive data

    //TODO create new order method

    //TODO create save method

    //TODO create delete method

    //TODO create tutorial
}
