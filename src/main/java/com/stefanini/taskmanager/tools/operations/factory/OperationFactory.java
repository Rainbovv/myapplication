package com.stefanini.taskmanager.tools.operations.factory;

import com.stefanini.taskmanager.tools.operations.*;
import joptsimple.OptionParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class OperationFactory implements AbstractOperationFactory {

    private OptionParser parser;

    @Override
    public Operation getOperation(String[] args) {
        switch (args[0]) {
            case "-createUser":
                return new CreateUser(args, parser);

            case "-showAllUsers":
                return new ShowAllUsers();

            case "-addTask":
                return new AddTask(args, parser);

            case "-showTasks":
                return new ShowUserTasks(args, parser);

            default:
                throw new NotImplementedException();
        }
    }

    private static class SingletonHolder {
        private static final OperationFactory INSTANCE = new OperationFactory();
    }

    public static OperationFactory getInstance() {
        if (SingletonHolder.INSTANCE.parser == null)
            SingletonHolder.INSTANCE.parser = new OptionParser();

        return SingletonHolder.INSTANCE;
    }
}
