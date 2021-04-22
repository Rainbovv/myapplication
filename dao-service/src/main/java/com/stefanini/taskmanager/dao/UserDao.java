package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.User;

import java.sql.SQLException;

public interface UserDao extends Dao<User> {

    User getUserByUserName(String userName) throws SQLException;
}
