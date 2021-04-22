package com.stefanini.taskmanager.operation.factory;

import com.stefanini.taskmanager.operation.*;
import com.stefanini.taskmanager.operation.pivot.AddTask;
import com.stefanini.taskmanager.operation.user.CreateUser;
import com.stefanini.taskmanager.operation.user.ShowAllUsers;
import com.stefanini.taskmanager.operation.pivot.ShowUserTasks;
import joptsimple.OptionParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OperationFactoryImpl implements OperationFactory {

    private OptionParser parser;
    private Logger logger;

    /**
     * Takes the program arguments, parse the first one and returns an operation.
     * @param args String[] of program arguments.
     * @return an operation object according to the the first argument;
     */
    @Override
    public Operation getOperation(String[] args) {

        logger.trace("getOperation method started!");

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
                return new NullOperation();
        }
    }

    private static class SingletonHolder {
        private static final OperationFactoryImpl INSTANCE = new OperationFactoryImpl();
    }

    public static OperationFactoryImpl getInstance() {

        OperationFactoryImpl operationFactory = SingletonHolder.INSTANCE;

        if (operationFactory.parser == null)
            operationFactory.parser = new OptionParser();
        if (operationFactory.logger == null)
            operationFactory.logger = LogManager.getLogger(OperationFactoryImpl.class);

        return operationFactory;
    }
}
