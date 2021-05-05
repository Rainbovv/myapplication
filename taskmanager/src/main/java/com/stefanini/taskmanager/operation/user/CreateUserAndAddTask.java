package com.stefanini.taskmanager.operation.user;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.impl.TaskServiceImpl;
import org.apache.logging.log4j.LogManager;

public class CreateUserAndAddTask extends UserOperation {

    private User user;
    private Task task;
    private TaskService taskService;

    public CreateUserAndAddTask(User user, Task task) {
        super();
        logger = LogManager.getLogger(AddTask.class);
        logger.trace("Creating CreateUserAndAddTask operation!");
        this.user = user;
        this.task = task;
        taskService = TaskServiceImpl.getInstance();
    }

    @Override
    public void run() {
        logger.trace("CreateUserAndAddTask running...");

            Task task = taskService.findByTitle(this.task.getTitle());

            if (task == null) task = this.task;

            else logger.warn("Task already exists!");

            userService.createUserAndAddTask(user, task);
    }
}

