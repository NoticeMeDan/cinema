package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.SeatEntity;

public class SeatService extends BaseService {
    public SeatService() { super(); }

    public void bookSeat(int seatNumber, int showId, int orderId) {
        SeatEntity seatEntity = new SeatEntity();
        seatDao.bookSeat(seatNumber, showId, orderId);
    }

    public void deleteSeatBooking(int seatNumber, int orderId) {
        SeatEntity seatEntity = new SeatEntity();
        seatDao.deleteSeatBooking(seatNumber, orderId);
    }
}
