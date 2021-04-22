package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.config.DataSourceProvider;
import com.stefanini.taskmanager.dao.AbstractDao;
import com.stefanini.taskmanager.dao.UserTaskDao;
import com.stefanini.taskmanager.entities.UserTaskRelation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserTaskDaoImpl extends AbstractDao<UserTaskRelation> implements UserTaskDao {


    private UserTaskDaoImpl() {}

    @Override
    protected UserTaskRelation convertToObject(ResultSet resultSet) throws SQLException {

        UserTaskRelation relation = new UserTaskRelation();
        relation.setUserId(resultSet.getLong("user_id"));
        relation.setTaskId(resultSet.getLong("task_id"));

        return relation;
    }

    @Override
    public boolean update(UserTaskRelation entity) throws SQLException {
        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("UPDATE user_task SET user_id=?, task_id=? WHERE id=?");

        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getTaskId());
        preparedStatement.setLong(3, entity.getId());

        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean create(UserTaskRelation entity) throws SQLException {
        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("INSERT INTO user_task VALUES (null ,? ,?)");
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getTaskId());

        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean remove(UserTaskRelation entity) throws SQLException {
        Statement statement = DataSourceProvider.getMysqlConnection().createStatement();

        return statement.executeUpdate("DELETE FROM user_task WHERE id=" + entity.getId()) == 1;
    }

    @Override
    public List<UserTaskRelation> getAll() throws SQLException {
        Statement statement = DataSourceProvider.getMysqlConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user_task");

        List<UserTaskRelation> relations = new ArrayList<>();

        while (resultSet.next()) {

            UserTaskRelation relation = convertToObject(resultSet);

            relations.add(relation);
        }
        return relations;
    }

    @Override
    public List<UserTaskRelation> getAllByUserId(Long userId) throws SQLException {
        Statement statement = DataSourceProvider.getMysqlConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user_task WHERE user_id=" + userId);

        List<UserTaskRelation> relations = new ArrayList<>();

        while (resultSet.next()) {

            UserTaskRelation relation = convertToObject(resultSet);

            relations.add(relation);
        }
        return relations;
    }

    public boolean addTaskToUser(String taskTitle, String userName) throws SQLException {

        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("SELECT u.id as user_id, t.id as task_id FROM users u " +
                        "JOIN tasks t ON u.user_name =? AND t.title=?");
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, taskTitle);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return create(convertToObject(resultSet));
    }

    private static class SingletonHolder {
        private static final UserTaskDaoImpl INSTANCE = new UserTaskDaoImpl();
    }

    public static UserTaskDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
