package com.stefanini.taskmanager.dao.factory;

import org.hibernate.cfg.NotYetImplementedException;

import com.stefanini.taskmanager.entities.AbstractEntity;
import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;

public class FactoryProvider {
	
	public static <T extends AbstractEntity> AbstractRepositoryFactory getFactory(
			Class<T> entityClass) {
		
		if (entityClass == User.class)
			return new UserRepositoryFactory();
		
		if (entityClass == Task.class)
			return new TaskRepositoryFactory();
		else
			throw new NotYetImplementedException();
	}
} 
