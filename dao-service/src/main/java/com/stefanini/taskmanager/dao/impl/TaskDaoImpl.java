package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.dao.AbstractDao;
import com.stefanini.taskmanager.dao.TaskDao;
import com.stefanini.taskmanager.entities.Task;
import javax.persistence.Query;

public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    private TaskDaoImpl() {}

    @Override
    public Task getByTitle(String title) {
        checkTransaction();

        Query query = entityManager.createQuery(
                "FROM " + getEntityClass().getName() + " t WHERE t.title=:title");
        query.setParameter("title", title);

        return (Task)query.getSingleResult();
    }

    private static class SingletonHolder {
        private static final TaskDaoImpl INSTANCE = new TaskDaoImpl();
    }

    public static TaskDaoImpl getInstance() {
        return TaskDaoImpl.SingletonHolder.INSTANCE;
    }
}
