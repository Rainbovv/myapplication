package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.dao.impl.UserTaskDaoImpl;
import com.stefanini.taskmanager.entities.Task;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserTaskServiceImpl {

    private UserDaoImpl userDaoImpl = DaoFactoryImpl.getInstance().getUserDao();
    private TaskDaoImpl taskDaoImpl = DaoFactoryImpl.getInstance().getTaskDao();
    private UserTaskDaoImpl relationDao = DaoFactoryImpl.getInstance().getRelationDao();

    private UserTaskServiceImpl(){}

    public boolean addTask(Task task, String userName) {

        try {
            try {
                taskDaoImpl.create(task);

            } catch (SQLException throwable) {
                System.err.println("Such task exists already!");;
            }
            return relationDao.addTaskToUser(task.getTitle(), userName);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public List<Task> findAllUserTasks(String userName) {

        List<Task> tasks = new ArrayList<>();
        try {
            tasks = taskDaoImpl.getAllByUserName(userName);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return tasks;
    }

    private static class SingletonHolder {
        private static final UserTaskServiceImpl INSTANCE = new UserTaskServiceImpl();
    }

    public static UserTaskServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
