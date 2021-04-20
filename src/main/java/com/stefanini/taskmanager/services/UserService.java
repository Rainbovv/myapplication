package com.stefanini.taskmanager.services;

import java.util.List;

import com.stefanini.taskmanager.dao.UserRepository;
import com.stefanini.taskmanager.dao.factory.RepositoryFactory;
import com.stefanini.taskmanager.entities.User;

public class UserService {

	private UserRepository userRepo = RepositoryFactory.getInstance().getUserRepository();

	private UserService() {}

	public Boolean create(User user) {
		return userRepo.create(user);
	}

	public List<User> findAllUsers() {
		return userRepo.getAll();
	}

	public User findUserByUserName(String userName) {
		return userRepo.getUserByUserName(userName);
	}

	public Boolean update(User user) {
		return userRepo.update(user);
	}

	private static class SingletonHolder {
		private static final UserService INSTANCE = new UserService();
	}

	public static UserService getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
