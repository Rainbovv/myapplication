package com.stefanini.taskmanager.service.impl;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl {

	private Logger logger;
	private UserDaoImpl userDao;

	private UserServiceImpl() {}

	public boolean create(User user) {

		logger.trace("create() started!");
		try {
			userDao.create(user);

			logger.trace("New user created!");

			return true;

		} catch (SQLException throwable) {
			if (throwable instanceof SQLIntegrityConstraintViolationException)
				logger.error("Such user already exists!");
			logger.error(throwable.getMessage());
		}

		return false;
	}

	public List<User> findAllUsers() {

		logger.trace("findAllUsers() started!");

		List<User> users = new ArrayList<>();

		try {
			users = userDao.getAll();

		} catch (SQLException throwable) {
			logger.error(throwable.getMessage());
		}

		logger.debug("Users found: " + users.size());
		return users;
	}

	public User findUserByUserName(String userName) {
		try {
			return userDao.getUserByUserName(userName);

		} catch (SQLException throwable) {
			logger.error(throwable.getMessage());
		}
		return null;
	}

	public Boolean update(User user) {
		try {
			return userDao.update(user);

		} catch (SQLException throwable) {
			logger.error(throwable.getMessage());
		}
		return false;
	}

	private static class SingletonHolder {
		private static final UserServiceImpl INSTANCE = new UserServiceImpl();
	}

	public static UserServiceImpl getInstance() {
		UserServiceImpl userService = SingletonHolder.INSTANCE;

		if (userService.logger == null)
			userService.logger = LogManager.getLogger(UserServiceImpl.class);
		if (userService.userDao == null)
			userService.userDao = DaoFactoryImpl.getInstance().getUserDao();

		return userService;
	}
}
