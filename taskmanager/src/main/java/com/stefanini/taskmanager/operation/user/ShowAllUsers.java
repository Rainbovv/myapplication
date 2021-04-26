package com.stefanini.taskmanager.operation.user;

import org.apache.logging.log4j.LogManager;

public class ShowAllUsers extends UserOperation {

    public ShowAllUsers() {
        logger = LogManager.getLogger(ShowAllUsers.class);
        logger.trace("Creating ShowAllUser operation object!");
    }

    @Override
    public void execute() {

        logger.trace("execute() started");
        userService.findAllUsers().forEach(System.out::println);
    }
}
