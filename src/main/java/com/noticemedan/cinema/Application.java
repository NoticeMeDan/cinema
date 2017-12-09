package com.noticemedan.cinema;

import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.CustomerService;
import com.noticemedan.cinema.service.TestService;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        TestService testService = new TestService();
        List<ShowEntity> shows = testService.getShows();

        System.out.println("Hey");
    }
}