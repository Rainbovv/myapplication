package com.stefanini.taskmanager.operation.user;

import org.apache.logging.log4j.LogManager;


public class ShowUserTasks extends UserOperation {

    private String userName;

    public ShowUserTasks(String userName) {
        super();
        this.userName = userName;
        logger = LogManager.getLogger(ShowUserTasks.class);
        logger.trace("Creating ShowUserTasks operation!");
    }

    @Override
    public void run() {
        logger.trace("ShowUserTasks running...");

            userService.findUserAllTasks(userName)
                    .forEach(System.out::println);
    }
}
