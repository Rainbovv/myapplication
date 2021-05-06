package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.config.PersistenceProvider;
import com.stefanini.taskmanager.entities.AbstractEntity;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.stream.Stream;

public abstract class AbstractDao<T extends AbstractEntity> implements Dao<T> {

    protected EntityManager entityManager;
    protected abstract Class<T> getEntityClass();

    protected AbstractDao() {
        this.entityManager = PersistenceProvider.getEntityManagerFactory()
                .createEntityManager();
    }

    @Override
    public T create(T entity) {
        checkTransaction();

        entityManager.persist(entity);
        entityManager.flush();

        return entity;
    }

    @Override
    public T update(T entity) {
        checkTransaction();

        return entityManager.merge(entity);
    }

    @Override
    public void remove(T entity) {
        checkTransaction();

        entityManager.remove(entityManager.contains(entity) ?
        entity : entityManager.merge(entity));
    }

    @Override
    public Stream<T> getAll() {
        checkTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
        Root<T> root = criteria.from(getEntityClass());
        criteria.select(root);

        return entityManager.createQuery(criteria).getResultStream();
    }

    public void commit() {
        entityManager.getTransaction().commit();
    }

    public void rollback() {
        entityManager.getTransaction().rollback();
    }

    protected void checkTransaction() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }
}
