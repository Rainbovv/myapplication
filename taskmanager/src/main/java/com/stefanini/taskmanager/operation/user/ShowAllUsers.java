package com.stefanini.taskmanager.operation.user;

import org.apache.logging.log4j.LogManager;

public class ShowAllUsers extends UserOperation {

    public ShowAllUsers() {
        logger = LogManager.getLogger(ShowAllUsers.class);
        logger.trace("Creating ShowAllUser operation!");
    }

    @Override
    public void run() {

        logger.trace("ShowAllUsers running...");
        userService.findAllUsers().forEach(System.out::println);
    }
}
