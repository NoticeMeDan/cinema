package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.SeatEntity;

import java.util.List;

public class SeatService extends BaseService {
    public SeatService() { super(); }

    public void bookSeat(String seatNumber, int showId, int orderId) {
        seatDao.bookSeat(seatNumber, showId, orderId);
    }

    public void deleteSeatBookings(int orderId) {
        seatDao.deleteSeatBooking(orderId);
    }

    public List<SeatEntity> getOrderSeats(int orderId) {
        return seatDao.getOrderSeats(orderId);
    }

    public List<SeatEntity> getBookedSeatsByShowId(int orderId) {
        return this.seatDao.getBookedSeatsByShowId(orderId);
    }

    public boolean doesOrderAlreadyExist(int orderId) {
        return this.getOrderSeats(orderId).size() > 0;
    }

}
