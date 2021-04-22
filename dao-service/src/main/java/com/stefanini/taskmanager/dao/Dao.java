package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T extends AbstractEntity> {

    boolean create(T entity) throws SQLException;
    boolean update(T entity) throws SQLException;
    boolean remove(T entity) throws SQLException;
    List<T> getAll() throws SQLException;
}
