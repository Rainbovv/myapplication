package com.stefanini.taskmanager.operation.user;

import com.stefanini.taskmanager.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;

public class ShowAllUsers extends UserOperation {

    public ShowAllUsers() {
        logger = LogManager.getLogger(ShowAllUsers.class);
        userService = UserServiceImpl.getInstance();
    }

    @Override
    public void execute() {
        userService.findAllUsers().forEach(System.out::println);
    }
}
