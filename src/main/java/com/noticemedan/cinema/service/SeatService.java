package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.SeatEntity;

import java.util.List;

public class SeatService extends BaseService {
    public SeatService() { super(); }

    public void bookSeat(int seatNumber, int showId, int orderId) {
        seatDao.bookSeat(seatNumber, showId, orderId);
    }

    public List<SeatEntity> getOrderSeats(int orderId) {
        return seatDao.getOrderSeats(orderId);
    }
}
