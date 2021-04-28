package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;

public class DaoFactoryImpl implements DaoFactory {

	private DaoFactoryImpl() {}

	@Override
	public UserDaoImpl getUserDao() {
		return UserDaoImpl.getInstance();
	}

	@Override
	public TaskDaoImpl getTaskDao() {
		return TaskDaoImpl.getInstance();
	}

	public static class SingletonHolder {
		private static final DaoFactoryImpl INSTANCE = new DaoFactoryImpl();
	}

	public static DaoFactoryImpl getInstance() {
		return SingletonHolder.INSTANCE;
	}
}