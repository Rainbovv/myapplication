package com.stefanini.taskmanager.tools.operations.impl.user;

import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.tools.operations.factory.OperationFactory;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreateUser extends UserOperationWithArgs {

    private Logger logger;

    public CreateUser(String[] args, OptionParser parser) {
        super(args, parser);
        this.logger = LogManager.getLogger(CreateUser.class);
    }

    @Override
    public void execute() {

        logger.trace("Starting arguments parsing");

        parser.accepts("createUser");
        parser.accepts("ln").withRequiredArg();
        parser.accepts("fn").withRequiredArg();
        parser.accepts("un").withRequiredArg();

        OptionSet options = parser.parse(args);

        userService.create(new User(options.valueOf("fn").toString(),
                options.valueOf("ln").toString(),
                options.valueOf("un").toString()));
    }
}
