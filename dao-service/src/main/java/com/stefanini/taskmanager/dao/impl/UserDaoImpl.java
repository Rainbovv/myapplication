package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.config.DataSourceProvider;
import com.stefanini.taskmanager.dao.AbstractDao;
import com.stefanini.taskmanager.dao.UserDao;
import com.stefanini.taskmanager.entities.User;
import org.apache.logging.log4j.LogManager;
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
    public Long create(User entity) {
        long id = -1;
        try {
            PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                    .prepareStatement("INSERT INTO users VALUES (null, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getUserName());

            if (preparedStatement.executeUpdate() != 1)
                return (long) -1;

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            id = resultSet.getLong(1);
            logger.trace("New user created!");

        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return id;
    }

    @Override
    public boolean update(User entity) {
        try {
            PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                    .prepareStatement("UPDATE users SET first_name=?, last_name=?, user_name=? WHERE id=?");

            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getUserName());
            preparedStatement.setLong(4, entity.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(User entity) {
        try {
            Statement statement = DataSourceProvider.getMysqlConnection().createStatement();

            return statement.executeUpdate("DELETE FROM users WHERE id=" + entity.getId()) == 1;
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = DataSourceProvider.getMysqlConnection()
                    .createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {

                User user = convertToObject(resultSet);

                users.add(user);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return users;
    }

    @Override
    public User getUserByUserName(String userName) {
        try {
            PreparedStatement preparedStatement = DataSourceProvider.getMysqlConnection()
                    .prepareStatement("SELECT * FROM users WHERE user_name=?");
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return convertToObject(resultSet);
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        }
        return null;
    }

    private static class SingletonHolder {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance() {
        if (SingletonHolder.INSTANCE.logger == null)
            SingletonHolder.INSTANCE.logger = LogManager.getLogger(UserDaoImpl.class);

        return SingletonHolder.INSTANCE;
    }
}
