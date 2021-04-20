package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.AbstractRepository;
import com.stefanini.taskmanager.entities.AbstractEntity;

public abstract class AbstractRepositoryFactory {
	
	public abstract <T extends AbstractEntity>  AbstractRepository<T> getRepo();
}
