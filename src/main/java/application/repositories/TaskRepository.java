package application.repositories;

import application.entities.Task;

import javax.persistence.Persistence;

public class TaskRepository extends AbstractRepository<Task> {

    private TaskRepository(){}

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    public static class SingletonHolder {

        private static final TaskRepository INSTANCE = new TaskRepository();
    }

    public static TaskRepository getInstance() {

        if (SingletonHolder.INSTANCE.entityManager == null)
            SingletonHolder.INSTANCE.entityManager = Persistence
                    .createEntityManagerFactory("local-pg")
                    .createEntityManager();

        return SingletonHolder.INSTANCE;
    }
}
