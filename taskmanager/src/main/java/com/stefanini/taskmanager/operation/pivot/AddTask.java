package com.stefanini.taskmanager.operation.pivot;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.service.impl.PivotServiceImpl;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.logging.log4j.LogManager;

public class AddTask extends PivotOperation {

    public AddTask(String[] args, OptionParser parser) {
        super(args, parser);
        logger = LogManager.getLogger(AddTask.class);
        relationService = PivotServiceImpl.getInstance();
        logger.trace("Creating AddTask operation object!");
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

            relationService.addTask(task, options.valueOf("un").toString());

        } catch (OptionException throwable) {
            logger.error(throwable.getMessage());
        }
    }
}
