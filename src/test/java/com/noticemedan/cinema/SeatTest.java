package com.noticemedan.cinema;

import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.SeatService;
import org.testng.Assert;
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
        try {
            this.seatService.bookSeat("8:4", 1, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetOrderSeats() throws Exception {
        List<SeatEntity> orderSeats = null;
        try {
            orderSeats = this.seatService.getOrderSeats(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(orderSeats.size() >= 3);
    }

    @Test
    public void testGetBookedSeatsByShowId() throws Exception {
        List<SeatEntity> bookedSeatsByShow = null;
        try {
            bookedSeatsByShow = this.seatService.getBookedSeatsByShowId(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(bookedSeatsByShow.size() == 0);
    }
}
