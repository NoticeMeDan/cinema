package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.service.SeatService;

public class SeatController {
    private SeatService seatService;

    public SeatController() {
        this.seatService = new SeatService();
    }

    public void bookSeat(int seatNumber, int showId, int orderId) {
        seatService.bookSeat(seatNumber, showId, orderId);
    }
}
