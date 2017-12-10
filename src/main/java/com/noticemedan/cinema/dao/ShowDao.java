package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.entity.mapper.ShowMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface ShowDao {
    @SqlQuery("SELECT *, S.ID as show_id, R.ID as room_id, M.ID as movie_id, T.ID as timeslot_id " +
            "FROM shows S " +
            "JOIN rooms R ON S.room_fk = R.id " +
            "JOIN movies M ON S.movie_fk = M.id " +
            "JOIN timeslots T ON S.timeslot_fk = T.id")
    @RegisterRowMapper(ShowMapper.class)
    List<ShowEntity> getShows();
}
