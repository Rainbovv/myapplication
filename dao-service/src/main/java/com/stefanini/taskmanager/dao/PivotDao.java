package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.UserTaskPivot;

import java.sql.SQLException;
import java.util.List;

public interface PivotDao extends Dao<UserTaskPivot> {

    List<UserTaskPivot> getAllByUserId(Long userId) throws SQLException;
}
