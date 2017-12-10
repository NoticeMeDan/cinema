package com.noticemedan.cinema.entity.mapper;

import com.noticemedan.cinema.entity.MovieEntity;
import com.noticemedan.cinema.entity.RoomEntity;
import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.entity.TimeSlotEntity;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowMapper implements RowMapper<ShowEntity> {
    @Override
    public ShowEntity map(ResultSet rs, StatementContext ctx) throws SQLException {
        RoomEntity room = new RoomEntity(
            rs.getInt("room_id"),
            rs.getInt("row_amount"),
            rs.getInt("column_amount")
        );

        MovieEntity movie = new MovieEntity(
            rs.getInt("movie_id"),
            rs.getString("title"),
            rs.getInt("duration")
        );

        TimeSlotEntity timeslot = new TimeSlotEntity(
            rs.getInt("timeslot_id"),
            rs.getTimestamp("start_time"),
            rs.getTimestamp("end_time")
        );

        return new ShowEntity(
            rs.getInt("show_id"),
            room,
            movie,
            timeslot
        );
    }
}
