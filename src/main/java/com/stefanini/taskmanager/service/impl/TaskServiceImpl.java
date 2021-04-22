package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.entities.Task;
import java.sql.SQLException;

public class TaskServiceImpl {

    private UserDaoImpl userDaoImpl = DaoFactoryImpl.getInstance().getUserDao();
    private TaskDaoImpl taskDaoImpl = DaoFactoryImpl.getInstance().getTaskDao();

    private TaskServiceImpl() {}

    public boolean create(Task task) {
        try {
            return taskDaoImpl.create(task);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    private static class SingletonHolder {
        private static final TaskServiceImpl INSTANCE = new TaskServiceImpl();
    }

    public static TaskServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
