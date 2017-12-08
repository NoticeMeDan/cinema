package com.noticemedan.cinema;

import com.noticemedan.cinema.dao.CustomerDao;
import com.noticemedan.cinema.entity.CustomerEntity;
import com.noticemedan.cinema.service.CustomerService;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomerTest {
    private CustomerDao customerDao;
    private CustomerService customerService;

    @BeforeMethod
    public void setUp() throws Exception {
        Jdbi jdbi = Jdbi.create("jdbc:h2:~/cinemadb/cinema;AUTO_SERVER=true");
        jdbi.installPlugin(new H2DatabasePlugin());
        jdbi.installPlugin(new SqlObjectPlugin());

        this.customerDao = jdbi.onDemand(CustomerDao.class);
        this.customerService = new CustomerService();
    }

    /*@Test
    public void testOrderCreation() throws Exception {
        this.customerService.saveCustomerOrder("12345678", 1);
    }*/
}