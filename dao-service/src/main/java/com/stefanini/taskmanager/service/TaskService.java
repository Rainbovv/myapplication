package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.entities.Task;

public interface TaskService extends Service<Task> {

    /**
     * Receives a title which is used to select a Task from DB
     * using TaskDao
     * @param title of type String
     * @return  an entity of type Task
     */
    public Task findByTitle(String title);
}
