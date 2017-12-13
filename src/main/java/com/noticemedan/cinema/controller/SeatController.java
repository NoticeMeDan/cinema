package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.service.SeatService;
import java.util.List;

public class SeatController {
    private SeatService seatService;

    public SeatController() {
        this.seatService = new SeatService();
    }

    public void bookSeat(List<String> seatNumber, int showId, int orderId) {
        seatService.bookSeat(seatNumber, showId, orderId);
    }

    public List<SeatEntity> getOrderSeats(int orderId) {
        return seatService.getOrderSeats(orderId);
    }

    public List<SeatEntity> getBookedSeatsByShowId(int orderId) {
        return seatService.getBookedSeatsByShowId(orderId);
    }
}
