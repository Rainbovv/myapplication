package com.stefanini.taskmanager.tools.operations.impl.user;

import joptsimple.OptionParser;

public class ShowUserTasks extends UserOperationWithArgs {

    public ShowUserTasks(String[] args, OptionParser parser) {
        super(args, parser);
    }

    @Override
    public void execute() {
        parser.accepts("un").withRequiredArg();
        userService.findUserByUserName(parser.parse(args).valueOf("un").toString())
                .getTasks().forEach(System.out::println);
    }
}
