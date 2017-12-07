package com.noticemedan.cinema;

import com.noticemedan.cinema.service.TestService;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {

        TestService testService = new TestService();

        System.out.println("Hey");
        System.out.println(Arrays.deepToString(testService.getTestsWithEasterEgg().toArray()));
    }
}