package com.stefanini.taskmanager.tools.operations.impl.user;

import com.stefanini.taskmanager.services.UserService;
import com.stefanini.taskmanager.tools.operations.Operation;

public abstract class UserOperation implements Operation {

    protected UserService userService = UserService.getInstance();
}
