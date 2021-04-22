package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class AbstractDao<T extends AbstractEntity> implements Dao<T> {

    protected abstract T convertToObject(ResultSet resultSet) throws SQLException;
}
