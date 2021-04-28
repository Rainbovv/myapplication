package com.stefanini.taskmanager;

import com.stefanini.taskmanager.config.PersistenceProvider;
import com.stefanini.taskmanager.dao.factory.DaoFactoryImpl;
import com.stefanini.taskmanager.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTest {

    private static UserDaoImpl userDao;
    private static TaskDaoImpl taskDao;
    private static User user;
    private static Task task;

    @BeforeAll
    public static void prepareData() {
        PersistenceProvider.setPersistenceUnitName("local-mysql-test");
        userDao = DaoFactoryImpl.getInstance().getUserDao();
        taskDao = DaoFactoryImpl.getInstance().getTaskDao();
        user = new User("TestFirstName", "TestLastName", "TestUserName" );
        task = new Task("TestTitle", "TestDescription");
    }

    @Order(1)
    @Test
    void mainCreateUser() {
        String[] args = new String[]{"-createUser", "-fn=" + user.getFirstName(),
                "-ln=" + user.getLastName(), "-un=" + user.getUserName()};
        Application.main(args);
        assertNotNull(userDao.getUserByUserName(user.getUserName()));
    }

    @Order(2)
    @Test
    void mainAddTask() {
        String[] args = new String[]{"-addTask", "-un=" + user.getUserName(),
                "-tt=" + task.getTitle(), "-td=" + task.getDescription()};
        Application.main(args);
        assertNotEquals(userDao.getUserByUserName("TestUserName").getTasks().size(), 0);
    }

    @Order(3)
    @Test
    void mainCreateUserAndAddTask() {
        removeData();
        String[] args = new String[]{"-createUserAndAddTask", "-fn=" + user.getFirstName(),
                "-ln=" + user.getLastName(), "-un=" + user.getUserName(),
                "-tt=" + task.getTitle(), "-td=" + task.getDescription()};
        Application.main(args);
        assertNotEquals(userDao.getUserByUserName("TestUserName").getTasks().size(), 0);
    }

    @AfterAll
    static void removeData() {
        user = userDao.getUserByUserName(user.getUserName());
        task = user.getTasks().get(0);
        userDao.remove(user);
        userDao.commit();
        taskDao.remove(task);
        taskDao.commit();
    }
}
