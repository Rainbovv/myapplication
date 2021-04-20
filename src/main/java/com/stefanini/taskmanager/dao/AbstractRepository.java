package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.AbstractEntity;

import java.util.List;

public interface AbstractRepository<T extends AbstractEntity> {
	
	Boolean create(T entity);
	Boolean update(T entity);
	Boolean remove(T entity);
	List<T> getAll();
}
