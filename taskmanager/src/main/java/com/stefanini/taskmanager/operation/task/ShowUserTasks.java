package com.stefanini.taskmanager.operation.task;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.logging.log4j.LogManager;

public class ShowUserTasks extends TaskOperation {

    public ShowUserTasks(String[] args, OptionParser parser) {
        super(args, parser);
        logger = LogManager.getLogger(ShowUserTasks.class);
        logger.trace("Creating ShowUserTasks operation object!");
    }

    @Override
    public void execute() {
        logger.trace("starting parse");

        parser.accepts("showTasks");
        parser.accepts("un").requiredIf("showTasks").withRequiredArg();

        try {
            OptionSet options = parser.parse(args);

            taskService.findAllUserTasks(options.valueOf("un").toString())
                    .forEach(System.out::println);
        } catch (OptionException throwable) {
            logger.error(throwable.getMessage());
        }
    }
}
