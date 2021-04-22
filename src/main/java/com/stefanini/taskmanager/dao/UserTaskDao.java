package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.UserTaskRelation;

import java.sql.SQLException;
import java.util.List;

public interface UserTaskDao extends Dao<UserTaskRelation> {

    List<UserTaskRelation> getAllByUserId(Long userId) throws SQLException;
}
