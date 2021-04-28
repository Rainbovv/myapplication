package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.User;

public interface UserDao extends Dao<User> {

    /**
     * Receives a String which is used to select a User by user_name from DB
     * @param userName of type String
     * @return an entity of type User if the record exists
     */
    User getUserByUserName(String userName);
}
