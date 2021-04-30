package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.Dao;
import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.entities.AbstractEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DaoFactoryImpl implements DaoFactory {

	private DaoFactoryImpl() {}

	@Override
	public Dao<? extends AbstractEntity>  getDao(DaoType daoType) {

		switch (daoType) {
			case TASKDAO:
				return TaskDaoImpl.getInstance();
			case USERDAO:
				return UserDaoImpl.getInstance();
			default:
				throw new NotImplementedException();
		}
	}

	public static class SingletonHolder {
		private static final DaoFactoryImpl INSTANCE = new DaoFactoryImpl();
	}

	public static DaoFactoryImpl getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
