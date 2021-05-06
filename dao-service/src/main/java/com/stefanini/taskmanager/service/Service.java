package com.stefanini.taskmanager.service;

public interface Service<T> {

    /**
     * Receives an entity and persists it in DB using Dao
     * @param entity
     * @return (rows affected == 1) ? true : false
     */
    T create(T entity);

    /**
     * Receives an entity and updates it in DB
     * using Dao class
     * @param entity
     * @return (rows affected == 1) ? true : false
     */
    T update(T entity);

    /**
     * Receives an entity and removes it from DB using Dao
     * @param entity
     */
    void remove(T entity);
}
