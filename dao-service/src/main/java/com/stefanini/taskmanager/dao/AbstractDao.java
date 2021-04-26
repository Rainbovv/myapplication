package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.AbstractEntity;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class AbstractDao<T extends AbstractEntity> implements Dao<T> {

    protected Logger logger;
    protected abstract T convertToObject(ResultSet resultSet) throws SQLException;
}
