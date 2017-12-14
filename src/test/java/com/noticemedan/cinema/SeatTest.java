package com.noticemedan.cinema;

import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.SeatService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SeatTest extends BaseService {
    private SeatService seatService;

    @BeforeMethod
    public void setUp() throws Exception {
        this.seatService = new SeatService();
    }

    @Test
    public void testSeatBooking() throws Exception {
        this.seatService.bookSeat("8:4", 1, 2);
        List<SeatEntity> orderSeats = this.seatService.getOrderSeats(2);
        List<SeatEntity> bookedSeatsByShow = this.seatService.getBookedSeatsByShowId(2);
    }

}
