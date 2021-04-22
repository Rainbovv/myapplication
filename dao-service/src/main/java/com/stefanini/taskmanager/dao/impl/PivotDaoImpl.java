package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.config.DataSourceProvider;
import com.stefanini.taskmanager.dao.AbstractDao;
import com.stefanini.taskmanager.dao.PivotDao;
import com.stefanini.taskmanager.entities.UserTaskPivot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PivotDaoImpl extends AbstractDao<UserTaskPivot> implements PivotDao {


    private PivotDaoImpl() {}

    @Override
    protected UserTaskPivot convertToObject(ResultSet resultSet) throws SQLException {

        UserTaskPivot relation = new UserTaskPivot();
        relation.setUserId(resultSet.getLong("user_id"));
        relation.setTaskId(resultSet.getLong("task_id"));

        return relation;
    }

    /**
     * Receives an entity of type UserTaskPivot and persists it in DB
     * @param entity of type UserTaskPivot
     * @return (rows affected == 1) ? true : false
     * @throws SQLException when database access error occurs
     */
    @Override
    public boolean create(UserTaskPivot entity) throws SQLException {
        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("INSERT INTO user_task VALUES (null ,? ,?)");
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getTaskId());

        return preparedStatement.executeUpdate() == 1;
    }

    /**
     * Receives an entity of type UserTaskPivot and updates it in DB
     * @param entity of type UserTaskPivot
     * @return (rows affected == 1) ? true : false
     * @throws SQLException when database access error occurs
     */
    @Override
    public boolean update(UserTaskPivot entity) throws SQLException {
        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("UPDATE user_task SET user_id=?, task_id=? WHERE id=?");

        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getTaskId());
        preparedStatement.setLong(3, entity.getId());

        return preparedStatement.executeUpdate() == 1;
    }

    /**
     * Receives an entity of type UserTaskPivot and removes it from DB
     * @param entity of type UserTaskPivot
     * @return (rows affected == 1) ? true : false
     * @throws SQLException when database access error occurs
     */
    @Override
    public boolean remove(UserTaskPivot entity) throws SQLException {
        Statement statement = DataSourceProvider.getMysqlConnection().createStatement();

        return statement.executeUpdate("DELETE FROM user_task WHERE id=" + entity.getId()) == 1;
    }

    /**
     * Selects all the records from DB.user_task
     * @return  a List of UserTaskPivot entities from persisted records
     * @throws SQLException when database access error occurs
     */
    @Override
    public List<UserTaskPivot> getAll() throws SQLException {
        Statement statement = DataSourceProvider.getMysqlConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user_task");

        List<UserTaskPivot> relations = new ArrayList<>();

        while (resultSet.next()) {

            UserTaskPivot relation = convertToObject(resultSet);

            relations.add(relation);
        }
        return relations;
    }

    /**
     * Receives a Long which is used to select all the records where user_id=userId
     * @return  a List of UserTaskPivot entities from persisted records
     * @throws SQLException when database access error occurs
     */
    @Override
    public List<UserTaskPivot> getAllByUserId(Long userId) throws SQLException {
        Statement statement = DataSourceProvider.getMysqlConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user_task WHERE user_id=" + userId);

        List<UserTaskPivot> relations = new ArrayList<>();

        while (resultSet.next()) {

            UserTaskPivot relation = convertToObject(resultSet);

            relations.add(relation);
        }
        return relations;
    }

    /**
     * Receives a title of Task and userName of User and adds a record in
     * pivot table.
     * @param taskTitle of type String
     * @param userName of type String
     * @return (rows affected == 1) ? true : false
     * @throws SQLException when database access error occurs
     */
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
        private static final PivotDaoImpl INSTANCE = new PivotDaoImpl();
    }

    public static PivotDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
