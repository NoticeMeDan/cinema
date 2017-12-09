package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.SeatEntity;

public class SeatService extends BaseService {
    public SeatService() { super(); }

    public void bookSeat(int seatNumber, int showId, int orderId) {
        SeatEntity seatEntity = new SeatEntity(showId, orderId, seatNumber);
        seatDao.bookSeat(seatEntity.getSeatNumber(), seatEntity.getShowId(), seatEntity.getOrderId());
    }

    public void deleteSeatBooking(int seatNumber, int orderId) {
        SeatEntity seatEntity = new SeatEntity(orderId, seatNumber);
        seatDao.deleteSeatBooking(seatEntity.getSeatNumber(), seatEntity.getOrderId());
    }
}
