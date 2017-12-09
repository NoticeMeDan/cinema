package com.noticemedan.cinema;

import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.SeatService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeatTest extends BaseService {
    private SeatService seatService;

    @BeforeMethod
    public void setUp() throws Exception {
        this.seatService = new SeatService();
    }

    @Test
    public void testSeatBooking() throws Exception {
    }

}
