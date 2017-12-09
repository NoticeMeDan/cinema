package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.ShowEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> commit through IntelliJ
import java.util.ResourceBundle;

public class UIController implements Initializable {
    //Customer ID and Login
    //TopPane
    @FXML private DatePicker pickDate;
    @FXML private Button login;
    @FXML private TextField customerID;
    @FXML private Label showID;
    @FXML private Label ld;
    //Pick info
    @FXML private DatePicker pickDate;
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
    public void login(){
        showID.setText(customerID.getText());
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
    public void newOrder(){
        //OrderController.createNewOrder();
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
