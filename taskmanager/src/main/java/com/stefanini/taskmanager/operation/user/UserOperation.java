package com.stefanini.taskmanager.operation.user;

import com.stefanini.taskmanager.service.impl.UserServiceImpl;
import com.stefanini.taskmanager.operation.Operation;
import org.apache.logging.log4j.Logger;

public abstract class UserOperation implements Operation {

    protected UserServiceImpl userService;
    protected Logger logger;

    public UserOperation() {
        this.userService = UserServiceImpl.getInstance();
    }
}
