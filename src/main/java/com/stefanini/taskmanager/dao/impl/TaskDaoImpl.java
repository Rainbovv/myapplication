package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.config.DataSourceProvider;
import com.stefanini.taskmanager.dao.AbstractDao;
import com.stefanini.taskmanager.entities.Task;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl extends AbstractDao<Task> implements com.stefanini.taskmanager.dao.TaskDao {

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
    public boolean create(Task entity) throws SQLException {
        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("INSERT INTO tasks VALUES (null,?,?)");
        preparedStatement.setString(1, entity.getDescription());
        preparedStatement.setString(2, entity.getTitle());

        return preparedStatement.executeUpdate() == 1;
    }

    public Task getByTitle(String title) throws SQLException {

        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("SELECT * FROM tasks WHERE title=?");
        preparedStatement.setString(1, title);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return convertToObject(resultSet);
    }

    @Override
    public boolean update(Task entity) throws SQLException {
        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("UPDATE tasks SET description=?, title=? WHERE id=?");

        preparedStatement.setString(1, entity.getDescription());
        preparedStatement.setString(2, entity.getTitle());
        preparedStatement.setLong(3, entity.getId());

        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean remove(Task entity) throws SQLException {
        Statement statement = DataSourceProvider.getMysqlConnection().createStatement();

        return statement.executeUpdate("DELETE FROM tasks WHERE id=" + entity.getId()) == 1;
    }

    @Override
    public List<Task> getAll() throws SQLException {
        Statement statement = DataSourceProvider.getMysqlConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");

        List<Task> tasks = new ArrayList<>();

        while (resultSet.next()) {

            Task task = convertToObject(resultSet);
            tasks.add(task);
        }
        return tasks;
    }

    public List<Task> getAllByUserName(String userName) throws SQLException {

        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("SELECT t.id, t.title, t.description FROM tasks t JOIN user_task ut ON " +
                        "t.id=ut.task_id JOIN users u ON ut.user_id=u.id WHERE u.user_name=?");
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Task> tasks = new ArrayList<>();

        while (resultSet.next()) {

            Task task = convertToObject(resultSet);
            tasks.add(task);
        }
        return tasks;
    }

    private static class SingletonHolder {
        private static final TaskDaoImpl INSTANCE = new TaskDaoImpl();
    }

    public static TaskDaoImpl getInstance() {
        return TaskDaoImpl.SingletonHolder.INSTANCE;
    }
}
