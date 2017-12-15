package com.noticemedan.cinema.view;

import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.SeatService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StateView {
    private static List<String> chosenSeats;
    private static List<String> bookedSeats;
    private static List<ShowEntity> availableShows;
    private static List<OrderView> showOrders = new ArrayList<>();
    private static LocalDate pickDateData;
    private static String pickMovieData = "";
    private static String pickTimeData = "";
    private static int ActiveOrder = 0;
    private static boolean validCustomer = false;

    public static int getActiveOrder() {
        return ActiveOrder;
    }

    public static void setActiveOrder(int activeOrder) {
        ActiveOrder = activeOrder;
    }

    public static boolean isValidCustomer() {
        return validCustomer;
    }

    public static void setValidCustomer(boolean validCustomer) {
        StateView.validCustomer = validCustomer;
    }

    public static List<String> getChosenSeats() {
        return chosenSeats;
    }

    public static void setChosenSeats(List<String> chosenSeats) {
        StateView.chosenSeats = chosenSeats;
    }

    public static Optional<ShowEntity> getSelectedShow() {
        // Find show matching picked movie and date
        List<ShowEntity> chosenShowList = getAvailableShows().stream()
                .filter(show -> {
                    String showTitle = show.getMovie().getName();
                    LocalDateTime showStartTime = show.getTimeslot().getStartTime().toLocalDateTime();
                    String pickedTitle = pickMovieData;
                    String pickedTime = pickTimeData;
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

    public static List<String> getBookedSeats() {
        Optional<ShowEntity> show = StateView.getSelectedShow();
        SeatService seatService = new SeatService();

        if (show.isPresent()) {
            List<SeatEntity> seats = seatService.getBookedSeatsByShowId(show.get().getId());
            return seats.stream()
                    .map(SeatEntity::getSeatNumber)
                    .distinct()
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public static void setBookedSeats(List<String> bookedSeats) {
        StateView.bookedSeats = bookedSeats;
    }

    public static List<ShowEntity> getAvailableShows() {
        return availableShows;
    }

    public static void setAvailableShows(List<ShowEntity> availableShows) {
        StateView.availableShows = availableShows;
    }

    public static List<OrderView> getShowOrders() {
        return showOrders;
    }

    public static void setShowOrders(List<OrderView> showOrders) {
        StateView.showOrders = showOrders;
    }

    public static LocalDate getPickDateData() {
        return pickDateData;
    }

    public static void setPickDateData(LocalDate pickDateData) {
        StateView.pickDateData = pickDateData;
    }

    public static String getPickMovieData() {
        return pickMovieData;
    }

    public static void setPickMovieData(String pickMovieData) {
        StateView.pickMovieData = pickMovieData;
    }

    public static String getPickTimeData() {
        return pickTimeData;
    }

    public static void setPickTimeData(String pickTimeData) {
        StateView.pickTimeData = pickTimeData;
    }
}
