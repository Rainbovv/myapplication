package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.settings.Settings;
import com.stefanini.taskmanager.tools.specifications.IsEqualSpecification;
import javax.persistence.Persistence;
import java.util.List;

public class TaskRepository extends BaseRepository<Task>{

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
        if (TaskRepository.SingletonHolder.INSTANCE.entityManager == null)
            TaskRepository.SingletonHolder.INSTANCE.entityManager = Persistence
                    .createEntityManagerFactory(Settings.getPersistenceProviderName())
                    .createEntityManager();

        return TaskRepository.SingletonHolder.INSTANCE;
    }
}
