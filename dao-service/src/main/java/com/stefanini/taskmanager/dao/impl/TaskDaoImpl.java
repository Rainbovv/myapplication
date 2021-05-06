package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.dao.AbstractDao;
import com.stefanini.taskmanager.dao.TaskDao;
import com.stefanini.taskmanager.entities.Task;


public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    private TaskDaoImpl() {}

    @Override
    public Task getByTitle(String title) {

        return getAll()
                .filter(t -> t.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    private static class SingletonHolder {
        private static final TaskDaoImpl INSTANCE = new TaskDaoImpl();
    }

    public static TaskDao getInstance() {
        return TaskDaoImpl.SingletonHolder.INSTANCE;
    }
}
