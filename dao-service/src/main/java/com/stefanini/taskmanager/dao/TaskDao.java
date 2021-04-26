package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entities.Task;
import java.util.List;
import java.util.Map;

public interface TaskDao extends Dao<Task> {

    /**
     * Receives a title which is used to select a Task from DB
     * @param title of type String
     * @return  an entity of type Task
     */
    Task getByTitle(String title);

    /**
     * Selects all the records assigned to the given user from DB.tasks
     * using JOIN and WHERE clauses
     * @param userName of type String
     * @return a List of Task entities from persisted records
     */
    List<Task> getAllByUserName(String userName);

    /**
     * Receives a title of Task and userName of User and gets their ids from DB
     * @param taskTitle of type String
     * @param userName of type String
     * @return Map<String, Long>
     */
    Map<String, Long> getUserAndTaskId(String taskTitle, String userName);

    /**
     * Receives a Map<String, Long> with ids of the User and Task and persists it in DB
     * @param ids Map<columnName of type String, id of type Long>
     * @return record persisted ? id of type Long : -1
     */
    Long addTaskToUser(Map<String, Long> ids);
}
