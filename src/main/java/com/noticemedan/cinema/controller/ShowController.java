package com.noticemedan.cinema.controller;

import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.ShowService;

import java.util.List;

public class ShowController extends BaseService {
    private ShowService showService;

    public ShowController() {
        this.showService = new ShowService();
    }

    public ShowEntity getSeatShow(int seatId) {
        return this.showService.getSeatShow(seatId);
    }
    
    public List<ShowEntity> getAvailableShowsByDate(String date) {
        return this.showService.getShowsById(date);
    }
}
