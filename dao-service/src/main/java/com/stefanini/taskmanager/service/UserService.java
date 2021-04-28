package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import java.util.List;

public interface UserService extends Service<User> {

    /**
     * Selects all the records from DB.users using UserDaoImpl class
     * @return  a List of User entities from persisted records
     */
    List<User> findAllUsers();

    /**
     * Receives a String which is used to select a User by user_name
     * from DB using UserDaoImpl class
     * @param userName of type String
     * @return an entity of type User if the record exists
     */
    User findUserByUserName(String userName);

    /**
     * Selects all the persisted records assigned to the given user
     * @param userName of type String
     * @return a List of Task entities from persisted records
     */
    List<Task> findUserAllTasks(String userName);

    /**
     * Receives a Task entity and an userName and persists it in DB
     * using TaskDaoImpl class
     * @param task entity of type Task
     * @param userName of type String
     * @return record persisted ? true : false
     */
    boolean addTask(Task task, String userName);
}
