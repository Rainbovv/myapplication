package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.entities.Task;

public interface TaskService extends Service<Task> {

    public Task findByTitle(String title);
}
