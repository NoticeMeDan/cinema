package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.ShowEntity;

import java.util.List;

public class ShowService extends BaseService {
    public final List<ShowEntity> getShows() {
        return this.showDao.getAllShows();
    }

    public ShowEntity getSeatShow(int showId) {
        return this.showDao.getShowById(showId);
    }

    public List<ShowEntity> getShowsByDate(String date) {
        return this.showDao.getShowsByDate(date);
    }
}
