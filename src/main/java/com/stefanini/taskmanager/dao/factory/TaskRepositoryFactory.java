package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.TaskRepository;

public class TaskRepositoryFactory extends AbstractRepositoryFactory {
	
	@Override
	public TaskRepository getRepo() {
		
		return TaskRepository.getInstance();
	}	
}
