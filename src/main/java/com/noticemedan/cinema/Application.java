package com.noticemedan.cinema;

import com.noticemedan.cinema.entity.OrderEntity;
import com.noticemedan.cinema.entity.SeatEntity;
import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.OrderService;
import com.noticemedan.cinema.service.SeatService;
import com.noticemedan.cinema.service.ShowService;

import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {

        SeatService seatService = new SeatService();
        List<SeatEntity> seats = seatService.getOrderSeats(1);

        System.out.println("Hey");
    }
}