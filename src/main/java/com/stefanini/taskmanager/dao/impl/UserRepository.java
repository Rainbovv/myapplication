package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.dao.BaseRepository;
import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.tools.specifications.IsEqualSpecification;
import java.util.List;

public class UserRepository extends BaseRepository<User> {

    private UserRepository() {}

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    public User getUserByUserName(String name) {
        List<User> users = super.findBySpecification(
                new IsEqualSpecification<>("userName",name));
        if (users.size() == 0)
            return null;
        return users.get(0);
    }

    public boolean removeByUserName(String userName) {
        return super.remove(getUserByUserName(userName));
    }

    private static class SingletonHolder {
        private static final UserRepository INSTANCE = new UserRepository();
    }

    public static UserRepository getInstance() {
        if (SingletonHolder.INSTANCE.entityManager == null)
            SingletonHolder.INSTANCE.entityManager = getEntityManager();

        return SingletonHolder.INSTANCE;
    }
}
