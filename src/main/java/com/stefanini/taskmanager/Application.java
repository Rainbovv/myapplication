package com.stefanini.taskmanager;

import com.stefanini.taskmanager.operation.*;
import com.stefanini.taskmanager.operation.factory.OperationFactoryImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class Application {

    static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {

        logger.trace("Application started.");
        logger.debug("Program arguments: " + Arrays.toString(args));

        if (args.length != 0) {
            Operation operation = OperationFactoryImpl.getInstance().getOperation(args);

            operation.execute();
        }
        else logger.error("Calling without arguments! Please use one of this arguments:" +
                "\n-addTask; -createUser; -showAllUsers; -showTasks");
    }
}
