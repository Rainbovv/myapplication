package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.AbstractEntity;

import java.util.stream.Stream;

public interface Dao<T extends AbstractEntity> {

    /**
     * Receives an entity of type (T extends AbstractEntity) and persists it in DB
     * @param entity of type (T extends AbstractEntity)
     * @return entity persisted ? id of the persisted entity of type Long : -1
     */
    T create(T entity);

    /**
     * Receives an entity of type (T extends AbstractEntity) and updates it in DB
     * @param entity of type (T extends AbstractEntity)
     * @return (rows affected == 1) ? true : false
     */
    T update(T entity);

    /**
     * Receives an entity of type (T extends AbstractEntity) and removes it from DB
     * @param entity of type (T extends AbstractEntity)
     */
    void remove(T entity);

    /**
     * Selects all the records from DB
     * @return a List entities from persisted records
     */
    Stream<T> getAll();

    /**
     * Ends transaction and commits changes to DB
     */
    void commit();

    /**
     *Rolls back the transaction.
     */
    void rollback();
}
