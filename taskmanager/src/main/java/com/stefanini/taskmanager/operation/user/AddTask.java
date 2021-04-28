package com.stefanini.taskmanager.operation.user;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.service.impl.TaskServiceImpl;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.logging.log4j.LogManager;

public class AddTask extends UserOperationWithArgs {

    TaskServiceImpl taskService;

    public AddTask(String[] args, OptionParser parser) {
        super(args, parser);
        logger = LogManager.getLogger(AddTask.class);
        logger.trace("Creating AddTask operation object!");
        taskService = TaskServiceImpl.getInstance();
    }

    @Override
    public void execute() {
        logger.trace("execute() started");
        logger.trace("Starting arguments parsing");

        parser.accepts("addTask");
        parser.accepts("tt").requiredIf("addTask").withRequiredArg();
        parser.accepts("td").requiredIf("addTask").withRequiredArg();
        parser.accepts("un").requiredIf("addTask").withRequiredArg();
        try {
            OptionSet options = parser.parse(args);

            Task task = new Task(options.valueOf("tt").toString(),
                    options.valueOf("td").toString());

            Task persistedTask = taskService.findByTitle(task.getTitle());

            if (persistedTask == null)
                userService.addTask(task, options.valueOf("un").toString());
            else
                userService.addTask(persistedTask, options.valueOf("un").toString());
        }
        catch (OptionException throwable) {
            logger.error(throwable.getMessage());
        }
    }
}
