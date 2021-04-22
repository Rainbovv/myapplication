package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.config.DataSourceProvider;
import com.stefanini.taskmanager.dao.AbstractDao;
import com.stefanini.taskmanager.dao.UserDao;
import com.stefanini.taskmanager.entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private UserDaoImpl() {}

    @Override
    protected User convertToObject(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setUserName(resultSet.getString("user_name"));

        return user;
    }
    @Override
    public boolean create(User entity) throws SQLException {

        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("INSERT INTO users VALUES (null, ?, ?, ?)");
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getUserName());

        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean update(User entity) throws SQLException {

        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("UPDATE users SET first_name=?, last_name=?, user_name=? WHERE id=?");

        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getUserName());
        preparedStatement.setLong(4, entity.getId());

        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean remove(User entity) throws SQLException {

            Statement statement = DataSourceProvider.getMysqlConnection().createStatement();

        return statement.executeUpdate("DELETE FROM users WHERE id=" + entity.getId()) == 1;
    }

    @Override
    public List<User> getAll() throws SQLException {

        Statement statement = DataSourceProvider.getMysqlConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

        List<User> users = new ArrayList<>();

        while (resultSet.next()) {

            User user = convertToObject(resultSet);

            users.add(user);
        }

        return users;
    }

    public User getUserByUserName(String userName) throws SQLException {

        PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                .prepareStatement("SELECT * FROM users WHERE user_name=?");
        preparedStatement.setString(1, userName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return convertToObject(resultSet);
    }

    private static class SingletonHolder {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
