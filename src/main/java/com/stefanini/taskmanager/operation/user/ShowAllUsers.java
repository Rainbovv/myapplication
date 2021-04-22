package com.stefanini.taskmanager.operation.user;

public class ShowAllUsers extends UserOperation {

    @Override
    public void execute() {
        userService.findAllUsers().forEach(System.out::println);
    }
}
