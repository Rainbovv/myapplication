package com.stefanini.taskmanager;

import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.operation.*;
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

//        UserDaoImpl userDao = DaoFactoryImpl.getInstance().getUserDao();
//        User user = new User("asdasd", "asdasdasd", "NewOne");
//        userDao.create(user);
////        user = userDao.getUserByUserName("NewOne");
//        user.setLastName("new");
//        userDao.update(user);
//        System.out.println(user);



        if (args.length != 0) {

            Operation operation = OperationFactoryImpl.getInstance().getOperation(args);

            operation.execute();
        }
        else logger.error("Calling without arguments! Please use one of this arguments:" +
                "\n-addTask; -createUser; -showAllUsers; -showTasks");
    }
}
