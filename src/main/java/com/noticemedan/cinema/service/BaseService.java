package com.noticemedan.cinema.service;

import com.noticemedan.cinema.dao.CustomerDao;
import com.noticemedan.cinema.dao.OrderDao;
import com.noticemedan.cinema.dao.SeatDao;
import com.noticemedan.cinema.dao.TestDao;
import com.noticemedan.cinema.entity.OrderEntity;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class BaseService {
    final TestDao testDao;
    final CustomerDao customerDao;
    final OrderDao orderDao;
    final SeatDao seatDao;

    public BaseService() {
        Jdbi jdbi = Jdbi.create("jdbc:h2:~/cinemadb/cinema;AUTO_SERVER=true");
        jdbi.installPlugin(new H2DatabasePlugin());
        jdbi.installPlugin(new SqlObjectPlugin());

        // Add more Dao's here as needed (kill me i'm so ugly)
        this.testDao = jdbi.onDemand(TestDao.class);
        this.customerDao = jdbi.onDemand(CustomerDao.class);
        this.orderDao = jdbi.onDemand(OrderDao.class);
        this.seatDao = jdbi.onDemand(SeatDao.class);
    }
}
