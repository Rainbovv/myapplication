package com.stefanini.taskmanager.repositories;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.tools.IsEqualSpecification;
import javax.persistence.Persistence;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task>{

    private TaskRepository() {}

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }
    public List<Task> getAllTasks() {
        return super.findBySpecification(null);
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
                    .createEntityManagerFactory("local-pg")
                    .createEntityManager();

        return TaskRepository.SingletonHolder.INSTANCE;
    }
}
