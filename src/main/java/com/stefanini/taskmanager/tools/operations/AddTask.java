package com.stefanini.taskmanager.tools.operations;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class AddTask extends UserOperationWithArgs {

    public AddTask(String[] args, OptionParser parser) {
        super(args, parser);
    }

    @Override
    public Boolean execute() {
        parser.accepts("addTask");
        parser.accepts("tt").withRequiredArg();
        parser.accepts("td").withRequiredArg();
        parser.accepts("un").withRequiredArg();
        OptionSet options = parser.parse(args);

        User user = userService.findUserByUserName(options.valueOf("un").toString());
        Task task = new Task(options.valueOf("tt").toString(),
                options.valueOf("td").toString());
        user.getTasks().add(task);

        return userService.update(user);
    }
}
