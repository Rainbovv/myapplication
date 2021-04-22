package com.stefanini.taskmanager.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl {

	private Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private UserDaoImpl userDaoImpl = DaoFactoryImpl.getInstance().getUserDao();

	private UserServiceImpl() {}

	public boolean create(User user) {
		try {
			return userDaoImpl.create(user);

		} catch (SQLException throwable) {
			logger.error(throwable.getMessage());
		}

		return false;
	}

	public List<User> findAllUsers() {

		List<User> users = new ArrayList<>();

		try {
			users = userDaoImpl.getAll();

		} catch (SQLException throwable) {
			logger.error(throwable.getMessage());
		}
		return users;
	}

	public User findUserByUserName(String userName) {
		try {
			return userDaoImpl.getUserByUserName(userName);

		} catch (SQLException throwable) {
			logger.error(throwable.getMessage());
		}
		return null;
	}

	public Boolean update(User user) {
		try {
			return userDaoImpl.update(user);

		} catch (SQLException throwable) {
			logger.error(throwable.getMessage());
		}
		return false;
	}

	private static class SingletonHolder {
		private static final UserServiceImpl INSTANCE = new UserServiceImpl();
	}

	public static UserServiceImpl getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
