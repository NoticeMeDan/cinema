package com.noticemedan.cinema.dao;

import com.noticemedan.cinema.entity.CustomerEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

import java.lang.reflect.Array;
import java.util.List;

public interface CustomerDao {
    @SqlUpdate("MERGE INTO customers " +
               "KEY(phone_number) " +
               "VALUES(:phone_number)")
    void saveCustomer(@Bind("phone_number") String phone_number);
}
