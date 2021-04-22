package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.entities.Task;

import java.util.List;

public interface PivotService {

    boolean addTask(Task task, String userName);
    List<Task> findAllUserTasks(String userName);
}
