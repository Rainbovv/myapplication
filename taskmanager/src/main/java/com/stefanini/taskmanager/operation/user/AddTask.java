package com.stefanini.taskmanager.operation.user;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.impl.TaskServiceImpl;
import org.apache.logging.log4j.LogManager;

public class AddTask extends UserOperation {

    private String userName;
    private Task task;
    private TaskService taskService;

    public AddTask(String userName, Task task) {
        super();
        logger = LogManager.getLogger(AddTask.class);
        logger.trace("Creating AddTask operation object!");
        this.task = task;
        this.userName = userName;
        taskService = TaskServiceImpl.getInstance();
    }

    @Override
    public void run() {
        logger.trace("execute() started");

        Task persistedTask = taskService.findByTitle(task.getTitle());

        if (persistedTask == null)
            userService.addTask(task, userName);
        else
            userService.addTask(persistedTask, userName);
    }
}
