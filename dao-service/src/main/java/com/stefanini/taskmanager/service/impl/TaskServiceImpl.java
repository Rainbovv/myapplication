package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.entities.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Map;

public class TaskServiceImpl {

    private Logger logger;
    private TaskDaoImpl taskDao;

    private TaskServiceImpl() {}

    public Long create(Task task) {

        logger.trace("create() started!");
        long result = taskDao.create(task);

        if (result == -1)
            logger.error("Such task already exists!");
        return result;
    }

    public List<Task> findAllUserTasks(String userName) {

        logger.trace("findAllUserTasks() started!");
        List<Task> tasks = taskDao.getAllByUserName(userName);

        logger.debug("Tasks found: " + tasks.size());
        return tasks;
    }

    public boolean addTask(Task task, String userName) {

        logger.trace("addTask() started!");
        boolean result = false;
        create(task);

        Map<String, Long> ids = taskDao.getUserAndTaskId(task.getTitle(), userName);

        if (ids.get("user_id") != null || ids.get("task_id") != null) {
            result = taskDao.addTaskToUser(ids) == 1;

            if (!result)
                logger.error("This task is already assigned to this user!");
            else
                logger.trace("Task added to user: " + userName);
        }
        return result;
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
