package application.repositories;

import application.entities.User;
import application.tools.IsEqualSpecification;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepository extends AbstractRepository<User>{

    private UserRepository() {}

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    public List<User> getAllUsers() {
        return super.findBySpecification(null);
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

    public static class SingletonHolder {
        private static final UserRepository INSTANCE = new UserRepository();
    }

    public static UserRepository getInstance() {
        if (SingletonHolder.INSTANCE.entityManager == null)
            SingletonHolder.INSTANCE.entityManager = Persistence
                    .createEntityManagerFactory("local-pg")
                    .createEntityManager();

        return SingletonHolder.INSTANCE;
    }
}