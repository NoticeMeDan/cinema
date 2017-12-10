package com.noticemedan.cinema;

import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.ShowService;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        ShowService showService = new ShowService();
        List<ShowEntity> shows = showService.getShows();

        System.out.println("Hey");
    }
}