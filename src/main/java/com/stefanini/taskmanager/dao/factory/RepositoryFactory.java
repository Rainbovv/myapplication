package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.impl.TaskRepository;
import com.stefanini.taskmanager.dao.impl.UserRepository;

public class RepositoryFactory implements AbstractRepositoryFactory {

	private RepositoryFactory() {}

	@Override
	public UserRepository getUserRepository() {
		return UserRepository.getInstance();
	}

	@Override
	public TaskRepository getTaskRepository() {
		return TaskRepository.getInstance();
	}

	public static class SingletonHolder {
		private static final RepositoryFactory INSTANCE = new RepositoryFactory();
	}

	public static RepositoryFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
