package com.stefanini.taskmanager.operation.user;

import com.stefanini.taskmanager.entities.User;
import org.apache.logging.log4j.LogManager;


public class CreateUser extends UserOperation {

    private User user;

    public CreateUser(User user) {
        super();
        logger = LogManager.getLogger(CreateUser.class);
        logger.trace("Creating CreateUser operation!");
        this.user = user;
    }

    @Override
    public void run() {

        logger.trace("CreateUser running...");

        userService.create(user);
    }
}
