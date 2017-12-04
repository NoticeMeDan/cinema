package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entities.TestEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface TestDao {
    @SqlQuery("SELECT * FROM test ORDER BY firstname")
    @RegisterBeanMapper(TestEntity.class)
    List<TestEntity> listTests();

}
