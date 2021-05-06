package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.dao.TaskDao;
import com.stefanini.taskmanager.dao.factory.DaoFactory;
import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;


public class TaskServiceImpl implements TaskService {

    private Logger logger;
    private TaskDao taskDao;

    private TaskServiceImpl() {}

    @Override
    public Task create(Task task) {
        try {
            task = taskDao.create(task);
            logger.trace("New task created!");
            taskDao.commit();
        }
        catch (PersistenceException exception) {
            logger.error("Task already exists!");
            taskDao.rollback();
        }
        catch (IllegalArgumentException exception) {
            logger.error(exception.getMessage());
            taskDao.rollback();
        }
        return task;
    }

    @Override
    public Task update(Task task) {
        try {
            taskDao.update(task);
            taskDao.commit();
        }
        catch (IllegalArgumentException | PersistenceException exception) {
            logger.error(exception.getMessage());
            taskDao.rollback();
        }
        return task;
    }

    @Override
    public void remove(Task task) {
        try {
            taskDao.remove(task);
            taskDao.commit();
        }
        catch (IllegalArgumentException exception) {
            logger.error(exception.getMessage());
            taskDao.rollback();
        }
    }

    @Override
    public Task findByTitle(String title) {
        try {
            return taskDao.getByTitle(title);
        }
        catch (NoResultException exception) {
            logger.error("No such task!");
        }
        return null;
    }

    private static class SingletonHolder {
        private static final TaskServiceImpl INSTANCE = new TaskServiceImpl();
    }

    public static TaskServiceImpl getInstance() {
        TaskServiceImpl taskService = SingletonHolder.INSTANCE;

        if (taskService.logger == null)
            taskService.logger = LogManager.getLogger(TaskServiceImpl.class);
        if (taskService.taskDao == null)
            taskService.taskDao = (TaskDao)DaoFactoryImpl.getInstance()
                    .getDao(DaoFactory.DaoType.TASKDAO);

        return taskService;
    }
}
