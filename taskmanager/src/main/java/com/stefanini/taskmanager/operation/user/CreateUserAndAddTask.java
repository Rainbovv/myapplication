package com.stefanini.taskmanager.operation.user;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.impl.TaskServiceImpl;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.logging.log4j.LogManager;

public class CreateUserAndAddTask extends UserOperationWithArgs {

    private TaskService taskService;

    public CreateUserAndAddTask(String[] args, OptionParser parser) {
        super(args, parser);
        logger = LogManager.getLogger(AddTask.class);
        logger.trace("Creating AddTask operation object!");
        taskService = TaskServiceImpl.getInstance();
    }

    @Override
    public void execute() {
        logger.trace("execute() started");
        logger.trace("Starting arguments parsing");

        parser.accepts("createUserAndAddTask");
        parser.accepts("ln").requiredIf("createUserAndAddTask").withRequiredArg();
        parser.accepts("fn").requiredIf("createUserAndAddTask").withRequiredArg();
        parser.accepts("un").requiredIf("createUserAndAddTask").withRequiredArg();
        parser.accepts("tt").requiredIf("createUserAndAddTask").withRequiredArg();
        parser.accepts("td").requiredIf("createUserAndAddTask").withRequiredArg();
        try {
            OptionSet options = parser.parse(args);

            Task task = taskService.findByTitle(options.valueOf("tt").toString());

            if (task == null) task = new Task(options.valueOf("tt").toString(),
                        options.valueOf("td").toString());
            else logger.warn("Task already exists!");

            User user = new User(options.valueOf("fn").toString(), options.valueOf("ln").toString(),
                    options.valueOf("un").toString());
            user = userService.createUserAndAddTask(user,task);
        }
        catch (OptionException throwable) {
            logger.error(throwable.getMessage());
        }
    }
}

