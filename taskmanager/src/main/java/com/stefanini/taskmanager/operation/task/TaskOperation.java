package com.stefanini.taskmanager.operation.task;

import com.stefanini.taskmanager.operation.Operation;
import com.stefanini.taskmanager.service.impl.TaskServiceImpl;
import joptsimple.OptionParser;
import org.apache.logging.log4j.Logger;

public abstract class TaskOperation implements Operation {

    protected TaskServiceImpl taskService;
    protected Logger logger;
    protected String[] args;
    protected OptionParser parser;

    public TaskOperation(String[] args, OptionParser parser) {
        this.args = args;
        this.parser = parser;
        taskService = TaskServiceImpl.getInstance();
    }
}
