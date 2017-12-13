package com.noticemedan.cinema.service;

import com.noticemedan.cinema.dao.*;
import com.noticemedan.cinema.entity.OrderEntity;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class BaseService {
    final CustomerDao customerDao;
    final OrderDao orderDao;
    final SeatDao seatDao;
    final ShowDao showDao;

    public BaseService() {
        Jdbi jdbi = Jdbi.create("jdbc:h2:./cinemadb/cinema;AUTO_SERVER=true;DB_CLOSE_DELAY=3600");
        jdbi.installPlugin(new H2DatabasePlugin());
        jdbi.installPlugin(new SqlObjectPlugin());

        // Add more Dao's here as needed
        this.customerDao = jdbi.onDemand(CustomerDao.class);
        this.orderDao = jdbi.onDemand(OrderDao.class);
        this.seatDao = jdbi.onDemand(SeatDao.class);
        this.showDao = jdbi.onDemand(ShowDao.class);
    }
}
