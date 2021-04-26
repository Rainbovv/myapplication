package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.config.DataSourceProvider;
import com.stefanini.taskmanager.dao.AbstractDao;
import com.stefanini.taskmanager.dao.TaskDao;
import com.stefanini.taskmanager.entities.Task;
import org.apache.logging.log4j.LogManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {

    private TaskDaoImpl() {}

    @Override
    protected Task convertToObject(ResultSet resultSet) throws SQLException {
        Task task = new Task();

        task.setId(resultSet.getLong("id"));
        task.setDescription(resultSet.getString("description"));
        task.setTitle(resultSet.getString("title"));

        return task;
    }

    @Override
    public Long create(Task entity) {
        long id = -1;
        try {
            PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                    .prepareStatement("INSERT INTO tasks VALUES (null,?,?)",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setString(2, entity.getTitle());

            if (preparedStatement.executeUpdate() != 1)
                return id;

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            id = resultSet.getLong(1);
            logger.trace("New task created!");

        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return id;
    }

    @Override
    public boolean update(Task entity) {
        try {
            PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                    .prepareStatement("UPDATE tasks SET description=?, title=? WHERE id=?");

            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setLong(3, entity.getId());

            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Task entity) {
        try {
            Statement statement = DataSourceProvider.getMysqlConnection().createStatement();

            return statement.executeUpdate("DELETE FROM tasks WHERE id=" + entity.getId()) == 1;
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return false;
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        try {
            Statement statement = DataSourceProvider.getMysqlConnection()
                    .createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");

            while (resultSet.next()) {

                Task task = convertToObject(resultSet);
                tasks.add(task);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return tasks;
    }

    public Task getByTitle(String title) {
        try {
            PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                    .prepareStatement("SELECT * FROM tasks WHERE title=?");

            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return convertToObject(resultSet);

        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return null;
    }

    public List<Task> getAllByUserName(String userName) {

        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                    .prepareStatement("SELECT t.id, t.title, t.description FROM tasks t JOIN user_task ut ON " +
                            "t.id=ut.task_id JOIN users u ON ut.user_id=u.id WHERE u.user_name=?");

            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Task task = convertToObject(resultSet);
                tasks.add(task);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return tasks;
    }

    public Long addTaskToUser(Map<String, Long> ids) {

        logger.trace("Starting addTaskToUser()...");
        long pivotId = -1;
        try {
            PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                    .prepareStatement("INSERT INTO user_task VALUES (null ,? ,?)",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, ids.get("user_id"));
            preparedStatement.setLong(2, ids.get("task_id"));

            if (preparedStatement.executeUpdate() != 1)
                return pivotId;

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            pivotId = resultSet.getLong(1);

        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return pivotId;
    }

    public Map<String, Long> getUserAndTaskId(String taskTitle, String userName) {

        Map<String, Long> ids = new HashMap<>();
        try {
            PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                    .prepareStatement("SELECT u.id as user_id, t.id as task_id FROM users u " +
                            "JOIN tasks t ON u.user_name =? AND t.title=?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, taskTitle);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            ids.put("user_id", resultSet.getLong("user_id"));
            ids.put("task_id", resultSet.getLong("task_id"));
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return ids;
    }

    private static class SingletonHolder {
        private static final TaskDaoImpl INSTANCE = new TaskDaoImpl();
    }

    public static TaskDaoImpl getInstance() {
        if (SingletonHolder.INSTANCE.logger == null)
            SingletonHolder.INSTANCE.logger = LogManager.getLogger(TaskDaoImpl.class);

        return TaskDaoImpl.SingletonHolder.INSTANCE;
    }
}
