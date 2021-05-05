package com.stefanini.taskmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.stefanini.taskmanager.dao.UserDao;
import com.stefanini.taskmanager.dao.factory.DaoFactory;
import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

public class UserServiceImpl implements UserService {

	private Logger logger;
	private UserDao userDao;

	private UserServiceImpl() {}

	@Override
	public User create(User user) {

		logger.trace("create() started!");
		try {
			userDao.create(user);
			logger.trace("New user created!");
			userDao.commit();
		}
		catch (PersistenceException exception) {
			logger.warn("User already exists!");
			userDao.rollback();
		}
		catch (IllegalArgumentException exception) {
			logger.error(exception.getMessage());
			userDao.rollback();
		}
		return user;
	}

	@Override
	public User update(User user) {

		logger.trace("update() started!");
		try {
			user = userDao.update(user);
			userDao.commit();
			logger.debug("User: " + user.getUserName() + " updated!");
		}
		catch (IllegalArgumentException | PersistenceException exception) {
			logger.error(exception.getMessage());
			userDao.rollback();
		}
		return user;
	}

	@Override
	public void remove(User user) {

		logger.trace("remove() started!");
		try {
			userDao.remove(user);
			userDao.commit();
			logger.debug("User: " + user.getUserName() + " removed!");
		}
		catch (IllegalArgumentException exception) {
			logger.error(exception.getMessage());
			userDao.rollback();
		}
	}

	@Override
	public List<User> findAllUsers() {

		logger.trace("findAllUsers() started!");

		List<User> users = userDao.getAll().collect(Collectors.toList());

		logger.debug("Users found: " + users.size());
		return users;
	}

	@Override
	public User findUserByUserName(String userName) {

		logger.trace("findUserByUserName() started!");
		try {
			return userDao.getUserByUserName(userName);
		}
		catch (NoResultException exception) {
			logger.error("No such user!");
		}
		return null;
	}

	@Override
	public List<Task> findUserAllTasks(String userName) {

		logger.trace("findUserAllTasks() started!");

		List<Task> tasks = new ArrayList<>();

		User user = findUserByUserName(userName);

		if (user != null) tasks = user.getTasks();

		logger.debug("Tasks found: " + tasks.size());

		return tasks;
	}

	@Override
	public boolean addTask(Task task, String userName) {

		logger.trace("addTask() started!");
		User user = findUserByUserName(userName);

		if (user != null) {
			user.getTasks().add(task);
			try {
				userDao.update(user);
				userDao.commit();
				logger.debug("The task with title: " + task.getTitle() +
						" attached to the user: " + user.getUserName());
				return true;
			}
			catch (IllegalArgumentException | IllegalStateException iException) {
				logger.error(iException.getMessage());
				userDao.rollback();
			}
		}
		return false;
	}

	@Override
	public User createUserAndAddTask(User user, Task task) {

		logger.trace("createUserAndAddTask() started!");
		try {
			try {
				user = userDao.getUserByUserName(user.getUserName());
				logger.error("User already exists!");
			}
			catch (PersistenceException exception) {

				userDao.create(user);
				logger.trace("New user created!");
			}
			user.getTasks().add(task);
			userDao.update(user);
			userDao.commit();
			logger.debug("The task with title: " + task.getTitle() +
					"attached to the user: " + user.getUserName());
		}
		catch (IllegalArgumentException | IllegalStateException iException) {
			logger.error(iException.getMessage());
			userDao.rollback();
			return null;
		}
		return user;
	}

	private static class SingletonHolder {
		private static final UserServiceImpl INSTANCE = new UserServiceImpl();
	}

	public static UserServiceImpl getInstance() {
		UserServiceImpl userService = SingletonHolder.INSTANCE;

		if (userService.logger == null)
			userService.logger = LogManager.getLogger(UserServiceImpl.class);
		if (userService.userDao == null)
			userService.userDao = (UserDao)DaoFactoryImpl.getInstance()
					.getDao(DaoFactory.DaoType.USERDAO);

		return userService;
	}
}
