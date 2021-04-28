package com.stefanini.taskmanager.operation.user;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.impl.TaskServiceImpl;
import joptsimple.OptionParser;
import org.apache.logging.log4j.LogManager;

public class CreateUserWithTasks extends UserOperationWithArgs {

    private TaskService taskService;

    CreateUserWithTasks(String[] args, OptionParser parser) {
        super(args, parser);
        logger = LogManager.getLogger(AddTask.class);
        logger.trace("Creating AddTask operation object!");
        taskService = TaskServiceImpl.getInstance();
    }

    @Override
    public void execute() {

    }
}
