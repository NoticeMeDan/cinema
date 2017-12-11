package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.ShowEntity;

import java.util.List;

public class ShowService extends BaseService {
    public final List<ShowEntity> getShows() {
        return this.showDao.getShows();
    }

    public ShowEntity getSeatShow(int seatId) {
        return this.showDao.getSeatShow(seatId);
    }
}
