package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.config.PersistenceProvider;
import com.stefanini.taskmanager.entities.AbstractEntity;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


public abstract class AbstractDao<T extends AbstractEntity> implements Dao<T> {

    protected EntityManager entityManager;
    protected abstract Class<T> getEntityClass();

    protected AbstractDao() {
        getEntityManager();
    }

    @Override
    @Transactional
    public T create(T entity) {
        checkTransaction();

        entityManager.persist(entity);
        entityManager.flush();

        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        checkTransaction();

        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public boolean remove(T entity) {
        checkTransaction();

        Query query = entityManager.createQuery("DELETE FROM "
                + getEntityClass().getName() + " e WHERE e.id = :entity_id");

        query.setParameter("entity_id", entity.getId());

        return query.executeUpdate() == 1;
    }

    @Override
    public List<T> getAll() {
        checkTransaction();

        Query query = entityManager.createQuery("FROM " + getEntityClass().getName() );

        return query.getResultList();
    }

    public void commit() {
        entityManager.getTransaction().commit();
    }

    protected void checkTransaction() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

    protected void getEntityManager() {

        if (entityManager == null)
        entityManager = PersistenceProvider.getEntityManagerFactory()
                .createEntityManager();
    }
}