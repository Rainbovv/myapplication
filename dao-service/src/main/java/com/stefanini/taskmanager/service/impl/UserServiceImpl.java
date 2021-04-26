package com.stefanini.taskmanager.service.impl;

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

	public Long create(User user) {

		logger.trace("create() started!");

		long result = userDao.create(user);

		if (result == -1)
				logger.error("Such user already exists!");

		return result;
	}

	public List<User> findAllUsers() {

		logger.trace("findAllUsers() started!");

		List<User> users = userDao.getAll();

		logger.debug("Users found: " + users.size());
		return users;
	}

	public User findUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	public Boolean update(User user) {
		return userDao.update(user);
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
