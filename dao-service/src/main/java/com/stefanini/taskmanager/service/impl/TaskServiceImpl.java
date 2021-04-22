package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.entities.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class TaskServiceImpl {

    private Logger logger;
    private TaskDaoImpl taskDao;

    private TaskServiceImpl() {}

    public boolean create(Task task) {

        logger.trace("create() started!");

        try {
            return taskDao.create(task);

        } catch (SQLException throwable) {
            if (throwable instanceof SQLIntegrityConstraintViolationException)
                logger.error("Such task already exists!");
        }
        return false;
    }

    private static class SingletonHolder {
        private static final TaskServiceImpl INSTANCE = new TaskServiceImpl();
    }

    public static TaskServiceImpl getInstance() {
        TaskServiceImpl taskService = SingletonHolder.INSTANCE;

        if (taskService.taskDao == null)
            taskService.taskDao = DaoFactoryImpl.getInstance().getTaskDao();
        if (taskService.logger == null)
            taskService.logger = LogManager.getLogger(TaskServiceImpl.class);

        return taskService;
    }
}
