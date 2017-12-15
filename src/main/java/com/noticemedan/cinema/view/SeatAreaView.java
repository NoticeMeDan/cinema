package com.noticemedan.cinema.view;

import com.noticemedan.cinema.controller.UIController;
import com.noticemedan.cinema.entity.RoomEntity;
import com.noticemedan.cinema.entity.ShowEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeatAreaView extends StateView {
    // Seats
    @FXML private Pane seat_group;
    @FXML private Label info;

    public void getInfo(){
        if (getPickMovieData() != null &&
                getPickDateData() != null &&
                getPickTimeData() != null) {
            info.setText("'" + getPickMovieData().toString() + "' ["
                    + getPickTimeData().toString() + "] - "
                    + getPickDateData().toString());
        } else {
            info.setText("Please select a valid date, movie and time.");
        }
    }

    public void updateSeats() {
        // Get booked seats for currently selected show
        setBookedSeats(this.getBookedSeats());

        // Remove chosen seats
        List<String> chosenSeats = getChosenSeats();
        chosenSeats.clear();
        setChosenSeats(chosenSeats);

        // Draw Seats
        this.drawSeats();
    }

    public void drawSeats() {
        // Get room of chosen show
        UIController uiController = new UIController();
        Optional<ShowEntity> show = uiController.getSelectedShow();
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
        int startDistanceY = 40;
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

                    if (getActiveOrder() != 0) {
                        if (this.isSeatChosen(number)) {
                            this.removeSeat(number);
                        } else {
                            this.chooseSeat(number);
                        }

                        // Redraw seats
                        this.drawSeats();
                    } else {
                        uiController.alertBox(
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
        Line screen = new Line(startDistanceX, 0, 320, 0);
        Label screenLabel = new Label("Screen");
        screenLabel.setLayoutX(startDistanceX);
        this.seat_group.getChildren().addAll(seats);
        this.seat_group.getChildren().addAll(screen, screenLabel);
    }

    public boolean isSeatChosen(String seatNumber) {
        return getChosenSeats().contains(seatNumber);
    }

    public boolean isSeatBooked(String seatNumber) {
        return getBookedSeats().contains(seatNumber);
    }

    private void chooseSeat(String seatNumber) {
        List<String> chosenSeats = getChosenSeats();
        chosenSeats.add(seatNumber);
        setChosenSeats(chosenSeats);
    }

    private void removeSeat(String seatNumber) {
        List<String> chosenSeats = getChosenSeats();
        chosenSeats.remove(seatNumber);
        setChosenSeats(chosenSeats);
    }

}
