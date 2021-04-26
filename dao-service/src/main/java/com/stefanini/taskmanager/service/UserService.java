package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.entities.User;
import java.util.List;

public interface UserService {

    /**
     * Receives an entity and persists it in DB using UserDaoImpl class
     * @param user of type User
     * @return (rows affected == 1) ? true : false
     */
    boolean create(User user);

    /**
     * Selects all the records from DB.users using UserDaoImpl class
     * @return  a List of User entities from persisted records
     */
    List<User> findAllUsers();

    /**
     * Receives a String which is used to select a User by user_name
     * from DB using UserDaoImpl class
     * @param userName of type String
     * @return an entity of type User if the record exists
     */
    User findUserByUserName(String userName);

    /**
     * Receives an entity of type User and updates it in DB
     * using UserDaoImpl class
     * @param user of type User
     * @return (rows affected == 1) ? true : false
     */
    Boolean update(User user);
}
