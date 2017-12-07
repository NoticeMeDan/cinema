package com.noticemedan.cinema.service;

import com.noticemedan.cinema.dao.TestDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

class BaseService {
    final TestDao testDao;

    BaseService() {
        Jdbi jdbi = Jdbi.create("jdbc:h2:~/cinemadb/cinema;AUTO_SERVER=true");
        jdbi.installPlugin(new H2DatabasePlugin());
        jdbi.installPlugin(new SqlObjectPlugin());

        // Add more Dao's here as needed (kill me i'm so ugly)
        this.testDao = jdbi.onDemand(TestDao.class);
    }
}
