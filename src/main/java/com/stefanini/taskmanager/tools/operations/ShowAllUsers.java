package com.stefanini.taskmanager.tools.operations;

public class ShowAllUsers extends UserOperation {

    @Override
    public Boolean execute() {
        userService.findAllUsers().forEach(System.out::println);
        return true;
    }
}
