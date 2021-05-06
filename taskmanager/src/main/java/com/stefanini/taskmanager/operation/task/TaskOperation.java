package com.stefanini.taskmanager.operation.task;

import com.stefanini.taskmanager.operation.Operation;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.impl.TaskServiceImpl;
import org.apache.logging.log4j.Logger;

public abstract class TaskOperation implements Operation {

    protected TaskService taskService;
    protected Logger logger;

    public TaskOperation() {
        this.taskService = TaskServiceImpl.getInstance();
    }

    @Override
    public void run() {

    }
}
