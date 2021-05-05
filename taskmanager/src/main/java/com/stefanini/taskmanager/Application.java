package com.stefanini.taskmanager;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.operation.user.AddTask;
import com.stefanini.taskmanager.operation.user.CreateUser;
import com.stefanini.taskmanager.operation.user.ShowAllUsers;
import com.stefanini.taskmanager.operation.user.ShowUserTasks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Application {

    static Logger logger;

    public static void main(String[] args) {

        logger = LogManager.getLogger(Application.class);

        logger.trace("Application started.");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter users first name:");
        String firstName = scanner.next();

        System.out.println("Please, enter users last name:");
        String lastName = scanner.next();

        System.out.println("Please, enter users user name:");
        String userName = scanner.next();

        System.out.println("Please, enter task title:");
        String title = scanner.next();

        System.out.println("Please, enter task description:");
        String description = scanner.next();

        ExecutorService service = Executors.newFixedThreadPool(4);
        try {
            service.submit(new CreateUser(new User(firstName, lastName, userName))).get();

            Future addTask = service.submit(new AddTask(userName, new Task(title, description)));

            service.execute(new ShowAllUsers());

            addTask.get();

            service.submit(new ShowUserTasks(userName)).get();
        }
        catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }

        service.shutdownNow();

        logger.trace("Application finished");
    }
}
