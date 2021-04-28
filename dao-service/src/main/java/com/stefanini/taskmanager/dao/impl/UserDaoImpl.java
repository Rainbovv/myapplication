package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.dao.AbstractDao;
import com.stefanini.taskmanager.dao.UserDao;
import com.stefanini.taskmanager.entities.User;
import javax.persistence.Query;
import javax.transaction.Transactional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    private UserDaoImpl() {}

    @Override
    public User getUserByUserName(String userName) {
        checkTransaction();

        Query query = entityManager.createQuery(
                "FROM " + getEntityClass().getName() + " u WHERE u.userName=:userName");
        query.setParameter("userName", userName);

        return (User)query.getSingleResult();
    }

    private static class SingletonHolder {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
