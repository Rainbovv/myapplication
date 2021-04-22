package com.stefanini.taskmanager.operation.user;

import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.service.impl.UserServiceImpl;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.logging.log4j.LogManager;


public class CreateUser extends UserOperationWithArgs {

    public CreateUser(String[] args, OptionParser parser) {
        super(args, parser);
        logger = LogManager.getLogger(CreateUser.class);
        userService = UserServiceImpl.getInstance();
    }

    @Override
    public void execute() {

        logger.trace("Starting arguments parsing");

        parser.accepts("createUser");
        parser.accepts("ln").requiredIf("createUser").withRequiredArg();
        parser.accepts("fn").requiredIf("createUser").withRequiredArg();
        parser.accepts("un").requiredIf("createUser").withRequiredArg();

        OptionSet options = parser.parse(args);

        userService.create(new User(options.valueOf("fn").toString(),
                options.valueOf("ln").toString(),
                options.valueOf("un").toString()));
    }
}
