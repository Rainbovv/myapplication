package com.stefanini.taskmanager;


import com.stefanini.taskmanager.operation.Operation;
import com.stefanini.taskmanager.operation.factory.OperationFactoryImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class Application {

    static Logger logger;

    public static void main(String[] args) {

        logger = LogManager.getLogger(Application.class);

        logger.trace("Application started.");
        logger.debug("Program arguments: " + Arrays.toString(args));

//        User user = new User("asdasd", "asdasd", "asdasd");
//
//        EmailSender emailSender = new EmailSender("radu.turcanu@extendaretail.com");
//
//        emailSender.send("Greeting","Hello there!");

        if (args.length != 0) {

            Operation operation = OperationFactoryImpl.getInstance().getOperation(args);

            operation.execute();
        }
        else logger.error("Calling without arguments! Please use one of this arguments:" +
                "\n-addTask; -createUser; -showAllUsers; -showTasks; -createUserAndAddTask");
    }
}
