package com.stefanini.taskmanager.tools.operations;

import com.stefanini.taskmanager.entities.User;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class CreateUser extends UserOperationWithArgs {

    public CreateUser(String[] args, OptionParser parser) {
        super(args, parser);
    }

    @Override
    public Boolean execute() {
        parser.accepts("createUser");
        parser.accepts("ln").withRequiredArg();
        parser.accepts("fn").withRequiredArg();
        parser.accepts("un").withRequiredArg();

        OptionSet options = parser.parse(args);

        return userService.create(new User(options.valueOf("fn").toString(),
                options.valueOf("ln").toString(),
                options.valueOf("un").toString()));
    }
}
