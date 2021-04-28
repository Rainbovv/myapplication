package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;


public class TaskServiceImpl implements TaskService {

    private Logger logger;
    private TaskDaoImpl taskDao;

    private TaskServiceImpl() {}

    @Override
    public Task create(Task task) {
        try {
            task = taskDao.create(task);
            logger.trace("New task created!");
            taskDao.commit();
        }
        catch (EntityExistsException throwable) {
            logger.error("Task already exists!");
        }
        catch (IllegalArgumentException throwable) {
            logger.error(throwable.getMessage());
        }
        return task;
    }

    @Override
    public Task update(Task task) {
        try {
            taskDao.update(task);
            taskDao.commit();
        }
        catch (IllegalArgumentException | PersistenceException throwable) {
            logger.error(throwable.getMessage());
        }
        return task;
    }

    @Override
    public boolean remove(Task task) {
        try {
            taskDao.remove(task);
            taskDao.commit();
            return true;
        }
        catch (IllegalArgumentException throwable) {
            logger.error(throwable.getMessage());
        }
        return false;
    }

    public Task findByTitle(String title) {
        try {
            return taskDao.getByTitle(title);
        }
        catch (NoResultException throwable) {
            logger.error("No such task!");
        }
        return null;
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
