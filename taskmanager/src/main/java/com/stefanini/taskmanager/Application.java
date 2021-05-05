package com.stefanini.taskmanager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Application {

    static Logger logger;

    public static void main(String[] args) {

        logger = LogManager.getLogger(Application.class);

        logger.trace("Application started.");


        System.out.println("Please, enter users first name:");
//            String u
//
//            ExecutorService executor = Executors.newFixedThreadPool(4);
//
//            executor.submit(() -> new ShowAllUsers().execute());
//            executor.submit(() -> new ShowUserTasks().execute());

    }
}
