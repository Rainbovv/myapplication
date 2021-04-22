package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.dao.impl.PivotDaoImpl;
import com.stefanini.taskmanager.entities.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class PivotServiceImpl {

    private UserDaoImpl userDao;
    private TaskDaoImpl taskDao;
    private PivotDaoImpl relationDao;
    private Logger logger;

    private PivotServiceImpl(){}

    /**
     * Persists a new record in tasks table if it doesn't exist.
     * Persists a new record in pivot table.
     * @param task of type Task
     * @param userName of type String
     * @return (rows affected == 1) ? true : false
     */
    public boolean addTask(Task task, String userName) {

        logger.trace("addTask() started!");

        try {
            try {
                taskDao.create(task);

            } catch (SQLException throwable) {
                logger.warn("Such task exists already!");
            }
            relationDao.addTaskToUser(task.getTitle(), userName);

            logger.trace("Task added to user: " + userName);

            return true;

        } catch (SQLException throwable) {
            if (throwable instanceof SQLIntegrityConstraintViolationException)
                logger.error("This task is already assigned to this user!");
            logger.error(throwable.getMessage());
        }
        return false;
    }

    /**
     * Selects all the records assigned to the given user using PivotDaoImpl class
     * @param userName of type String
     * @return  a List of Task entities from persisted records
     */
    public List<Task> findAllUserTasks(String userName) {

        logger.trace("findAllUserTasks() started!");

        List<Task> tasks = new ArrayList<>();
        try {
            tasks = taskDao.getAllByUserName(userName);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        logger.debug("Tasks found: " + tasks.size());
        return tasks;
    }

    private static class SingletonHolder {
        private static final PivotServiceImpl INSTANCE = new PivotServiceImpl();
    }

    public static PivotServiceImpl getInstance() {
        PivotServiceImpl relationService = SingletonHolder.INSTANCE;

        if (relationService.relationDao == null)
            relationService.relationDao = DaoFactoryImpl.getInstance().getRelationDao();
        if (relationService.userDao == null)
            relationService.userDao = DaoFactoryImpl.getInstance().getUserDao();
        if (relationService.taskDao == null)
            relationService.taskDao = DaoFactoryImpl.getInstance().getTaskDao();
        if (relationService.logger == null)
            relationService.logger = LogManager.getLogger(PivotServiceImpl.class);

        return relationService;
    }
}
