package com.stefanini.taskmanager.tools.operations.factory;

import com.stefanini.taskmanager.tools.operations.*;
import com.stefanini.taskmanager.tools.operations.impl.user.AddTask;
import com.stefanini.taskmanager.tools.operations.impl.user.CreateUser;
import com.stefanini.taskmanager.tools.operations.impl.user.ShowAllUsers;
import com.stefanini.taskmanager.tools.operations.impl.user.ShowUserTasks;
import joptsimple.OptionParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OperationFactory implements AbstractOperationFactory {

    private OptionParser parser;
    private Logger logger;

    /**
     * Takes the program arguments, parse the first one and returns an operation.
     * @param args String[] of program arguments.
     * @return an operation object according to the the first argument;
     */
    @Override
    public Operation getOperation(String[] args) {

        logger.trace("Checking the 1'st argument!");

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
                logger.error( "No such method: " + args[0] + ". Please use one of this arguments:" +
                        "\n-addTask; -createUser; -showAllUsers; -showTasks");
                return new AbstractOperation();
        }
    }

    private static class SingletonHolder {
        private static final OperationFactory INSTANCE = new OperationFactory();
    }

    public static OperationFactory getInstance() {
        if (SingletonHolder.INSTANCE.parser == null)
            SingletonHolder.INSTANCE.parser = new OptionParser();
        if (SingletonHolder.INSTANCE.logger == null)
            SingletonHolder.INSTANCE.logger = LogManager.getLogger(OperationFactory.class);


        return SingletonHolder.INSTANCE;
    }
}
