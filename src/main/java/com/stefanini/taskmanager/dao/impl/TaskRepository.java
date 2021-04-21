package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.dao.BaseRepository;
import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.tools.specifications.IsEqualSpecification;
import java.util.List;

public class TaskRepository extends BaseRepository<Task> {

    private TaskRepository() {}

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    public Task getTaskByTitle(String title) {
        List<Task> users = super.findBySpecification(
                new IsEqualSpecification<>("title", title));
        if (users.size() == 0)
            return null;
        return users.get(0);
    }

    public boolean removeByTitle(String title) {
        return super.remove(getTaskByTitle(title));
    }

    public static class SingletonHolder {
        private static final TaskRepository INSTANCE = new TaskRepository();
    }

    public static TaskRepository getInstance() {
        if (SingletonHolder.INSTANCE.entityManager == null)
            SingletonHolder.INSTANCE.entityManager = getEntityManager();

        return SingletonHolder.INSTANCE;
    }
}
