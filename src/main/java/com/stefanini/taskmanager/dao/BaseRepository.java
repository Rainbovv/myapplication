package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.AbstractEntity;
import com.stefanini.taskmanager.tools.specifications.AbstractSpecification;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public  abstract class BaseRepository<T extends AbstractEntity> implements AbstractRepository<T>{

    protected EntityManager entityManager;
    protected abstract Class<T> getEntityClass();

    @Override
    public List<T> getAll() {
        return findBySpecification(null);
    }

    @Override
    public Boolean create(T entity) {
        checkTransaction();
        try {
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(T entity) {
        checkTransaction();
        try {
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean remove(T entity) {
        checkTransaction();

        try {
            entityManager.remove(entityManager.contains(entity) ?
                    entity: entityManager.merge(entity));

            entityManager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<T> findBySpecification(AbstractSpecification<T> specification) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
        Root<T> root = criteria.from(getEntityClass());
        criteria.select(root);

        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, builder);
            criteria.where(predicate);
        }
        return entityManager.createQuery(criteria).getResultList();
    }

    protected void checkTransaction() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }
}
