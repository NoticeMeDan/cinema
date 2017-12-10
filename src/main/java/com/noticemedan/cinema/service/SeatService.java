package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.SeatEntity;

import java.util.List;

public class SeatService extends BaseService {
    public SeatService() { super(); }

    public void bookSeat(int seatNumber, int showId, int orderId) {
        SeatEntity seatEntity = new SeatEntity(showId, orderId, seatNumber);
        seatDao.bookSeat(seatEntity.getSeatNumber(), seatEntity.getShowId(), seatEntity.getOrderId());
    }

    public List<SeatEntity> getOrderSeats(int orderId) {
        SeatEntity seatEntity = new SeatEntity(orderId);
        return seatDao.getOrderSeats(seatEntity.getOrderId());
    }
}
