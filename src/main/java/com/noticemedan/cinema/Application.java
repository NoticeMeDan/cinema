package com.noticemedan.cinema;

import com.noticemedan.cinema.dao.TestDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {

        Jdbi jdbi = Jdbi.create("jdbc:h2:~/cinemadb/cinema;AUTO_SERVER=true");
        jdbi.installPlugin(new H2DatabasePlugin());
        jdbi.installPlugin(new SqlObjectPlugin());

        TestDao dao = jdbi.onDemand(TestDao.class);

        System.out.println("Hey");
        System.out.println(Arrays.deepToString(dao.listTests().toArray()));
    }
}
