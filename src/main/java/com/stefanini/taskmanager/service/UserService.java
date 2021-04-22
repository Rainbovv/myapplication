package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.entities.User;

import java.util.List;

public interface UserService {

    boolean create(User user);
    List<User> findAllUsers();
    User findUserByUserName(String userName);
    Boolean update(User user);
}
