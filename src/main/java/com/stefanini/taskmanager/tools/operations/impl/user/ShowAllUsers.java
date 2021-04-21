package com.stefanini.taskmanager.tools.operations.impl.user;

public class ShowAllUsers extends UserOperation {

    @Override
    public void execute() {
        userService.findAllUsers().forEach(System.out::println);
    }
}
