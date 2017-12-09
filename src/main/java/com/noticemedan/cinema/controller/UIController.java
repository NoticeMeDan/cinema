package com.noticemedan.cinema.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.net.URL;
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
    private ObservableList<String> time = FXCollections.observableArrayList("8", "12");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pickMovie.setItems(movies);
        pickTime.setItems(time);
    }
}
