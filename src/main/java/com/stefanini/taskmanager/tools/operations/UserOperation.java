package com.stefanini.taskmanager.tools.operations;

import com.stefanini.taskmanager.services.UserService;

public abstract class UserOperation implements Operation {

    protected UserService userService = UserService.getInstance();
}
