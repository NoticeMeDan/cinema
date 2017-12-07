package com.noticemedan.cinema;

import com.noticemedan.cinema.service.CustomerService;
import com.noticemedan.cinema.service.TestService;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {

        TestService testService = new TestService();
        CustomerService customerService = new CustomerService();

        System.out.println("Hey");
        System.out.println(Arrays.deepToString(testService.getTestsWithEasterEgg().toArray()));
        //System.out.println(Arrays.deepToString(customerService.getTestsWithEasterEgg().toArray()));
    }
}