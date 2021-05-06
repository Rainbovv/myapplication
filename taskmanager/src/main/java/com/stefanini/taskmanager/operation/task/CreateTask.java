package com.stefanini.taskmanager.operation.task;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.operation.user.CreateUser;
import org.apache.logging.log4j.LogManager;

public class CreateTask extends TaskOperation {

    private Task task;

    public CreateTask(Task task) {
        super();
        logger = LogManager.getLogger(CreateUser .class);
        logger.trace("Creating CreateTask operation!");
        this.task = task;
    }

    @Override
    public void run() {
        logger.trace("CreateTask running...");

        taskService.create(task);
    }
}
