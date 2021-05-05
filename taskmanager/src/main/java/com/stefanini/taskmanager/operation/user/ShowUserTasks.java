package com.stefanini.taskmanager.operation.user;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.logging.log4j.LogManager;


public class ShowUserTasks extends UserOperationWithArgs {

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

            userService.findUserAllTasks(options.valueOf("un").toString())
                    .forEach(System.out::println);

        } catch (OptionException exception) {
            logger.error(exception.getMessage());
        }
    }
}
