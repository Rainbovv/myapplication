package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.Task;

public interface TaskDao extends Dao<Task> {

    /**
     * Receives a title which is used to select a Task from DB
     * @param title of type String
     * @return  an entity of type Task
     */
    Task getByTitle(String title);

}
