package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.entities.Task;

import java.util.List;

public interface TaskService {

    /**
     * Receives an entity and persists it in DB
     * using TaskDaoImpl class
     * @param task of type Task
     * @return (rows affected == 1) ? true : false
     */
    boolean create(Task task);

    /**
     * Receives userName and gets all his tasks from DB
     * using TaskDaoImpl class
     * @param userName type String
     * @return a List of Task entities from persisted records
     */
    List<Task> findAllUserTasks(String userName);

    /**
     * Receives a Task entity and an userName and persists it in DB
     * using TaskDaoImpl class
     * @param task entity of type Task
     * @param userName of type String
     * @return record persisted ? if of the new record : -1
     */
    boolean addTask(Task task, String userName);
}
