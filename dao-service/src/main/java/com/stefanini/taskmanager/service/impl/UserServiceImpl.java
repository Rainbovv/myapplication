package com.stefanini.taskmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

public class UserServiceImpl implements UserService {

	private Logger logger;
	private UserDaoImpl userDao;

	private UserServiceImpl() {}

	@Override
	public User create(User user) {

		logger.trace("create() started!");
		try {
			userDao.create(user);
			logger.trace("New user created!");
			userDao.commit();
		}
		catch (PersistenceException throwable) {
			logger.error("User already exists!");
		}
		catch (IllegalArgumentException throwable) {
			logger.error(throwable.getMessage());
		}
		return user;
	}

	@Override
	public User update(User user) {
		try {
			user = userDao.update(user);
			userDao.commit();
		}
		catch (IllegalArgumentException | PersistenceException throwable) {
			logger.error(throwable.getMessage());
		}
		return user;
	}

	@Override
	public boolean remove(User user) {
		try {
			userDao.remove(user);
			userDao.commit();
			return true;
		}
		catch (IllegalArgumentException throwable) {
			logger.error(throwable.getMessage());
		}
		return false;
	}

	@Override
	public List<User> findAllUsers() {

		logger.trace("findAllUsers() started!");

		List<User> users = userDao.getAll();

		logger.debug("Users found: " + users.size());
		return users;
	}

	@Override
	public User findUserByUserName(String userName) {
		try {
			return userDao.getUserByUserName(userName);
		}
		catch (NoResultException throwable) {
			logger.error("No such user!");
		}
		return null;
	}

	@Override
	public List<Task> findUserAllTasks(String userName) {

		List<Task> tasks = new ArrayList<>();

		User user = findUserByUserName(userName);

		if (user != null) tasks = user.getTasks();

		logger.debug("Tasks found: " + tasks.size());

		return tasks;
	}

	@Override
	public boolean addTask(Task task, String userName) {

		User user = findUserByUserName(userName);

		if (user != null) {
			user.getTasks().add(task);
			update(user);
			return true;
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
