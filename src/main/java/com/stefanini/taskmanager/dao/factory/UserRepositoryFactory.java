package com.stefanini.taskmanager.dao.factory;
import com.stefanini.taskmanager.dao.UserRepository;

public class UserRepositoryFactory extends AbstractRepositoryFactory {
	
	@Override
	public UserRepository getRepo() {
		
		return UserRepository.getInstance();
	}	
}
