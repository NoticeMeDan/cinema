package com.noticemedan.cinema;

import com.noticemedan.cinema.controller.SeatController;
import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.SeatService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SeatTest extends BaseService {
    private SeatController seatController;

    @BeforeMethod
    public void setUp() throws Exception {
        this.seatController = new SeatController();
    }

    @Test
    public void testSeatBooking() throws Exception {
        this.seatController.bookSeat("8:4", 1, 1);
        List<SeatEntity> orderSeats = this.seatController.getOrderSeats(1);
        List<SeatEntity> bookedSeatsByShow = this.seatController.getBookedSeatsByShowId(1);
    }

}
