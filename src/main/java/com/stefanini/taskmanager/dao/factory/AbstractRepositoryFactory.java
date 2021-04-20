package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.TaskRepository;
import com.stefanini.taskmanager.dao.UserRepository;

public interface AbstractRepositoryFactory {

	UserRepository getUserRepository();
	TaskRepository getTaskRepository();
}
