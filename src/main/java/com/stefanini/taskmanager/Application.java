package com.stefanini.taskmanager;

import com.stefanini.taskmanager.tools.operations.*;
import com.stefanini.taskmanager.tools.operations.factory.OperationFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class Application {

    static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {

        logger.trace("Application started with arguments: " + Arrays.toString(args));

        if (args.length == 0) {
            logger.error("Calling without arguments! Please use one of this arguments:\n-addTask; -createUser; -showAllUsers; -showTasks");
            return;
        }

        Operation operation = OperationFactory.getInstance().getOperation(args);

        operation.execute();
    }
}
