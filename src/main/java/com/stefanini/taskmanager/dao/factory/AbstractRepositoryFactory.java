package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.impl.TaskRepository;
import com.stefanini.taskmanager.dao.impl.UserRepository;

public interface AbstractRepositoryFactory {

	UserRepository getUserRepository();
	TaskRepository getTaskRepository();
}
