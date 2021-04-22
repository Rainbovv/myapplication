package com.stefanini.taskmanager.dao.factory;


import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;

public interface DaoFactory {

	UserDaoImpl getUserDao();
	TaskDaoImpl getTaskDao();
}
