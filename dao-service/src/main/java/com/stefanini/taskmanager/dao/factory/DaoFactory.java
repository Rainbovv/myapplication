package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.Dao;
import com.stefanini.taskmanager.entities.AbstractEntity;

public interface DaoFactory {

	enum DaoType {
		USERDAO, TASKDAO
	}

	Dao<? extends AbstractEntity> getDao(DaoFactory.DaoType daoType);
}
