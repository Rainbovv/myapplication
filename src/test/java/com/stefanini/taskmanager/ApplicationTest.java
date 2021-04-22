package com.stefanini.taskmanager;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTest {

//    private static UserDao userDao;
//    private static TaskDao taskDao;
//    private static User user;
//    private static Task task;
//
//    @BeforeAll
//    public static void prepareData() {
//        userDao = DaoFactory.getUserDao();
//        taskDao = DaoFactory.getTaskDao();
//        user = new User("TestFirstName", "TestLastName", "TestUserName" );
//        task = new Task("TestTitle", "TestDescription");
//        userDao.removeByUserName(user.getUserName());
//        taskDao.removeByTitle(task.getTitle());
//    }
//
//    @Order(1)
//    @Test
//    void mainCreateUser() {
//        String[] args = new String[]{"-createUser", "-fn=" + user.getFirstName(),
//                "-ln=" + user.getLastName(), "-un=" + user.getUserName()};
//        Application.main(args);
//        assertNotNull(userDao.getUserByUserName(user.getUserName()));
//    }
//
//    @Order(2)
//    @Test
//    void mainAddTask() {
//        String[] args = new String[]{"-addTask", "-un=" + user.getUserName(),
//                "-tt=" + task.getTitle(), "-td=" + task.getDescription()};
//        Application.main(args);
//        assertNotEquals(userDao.getUserByUserName("TestUserName").getTasks().size(), 0);
//    }
//
//    @AfterAll
//    static void removeData() {
//        userDao.removeByUserName(user.getUserName());
//        taskDao.removeByTitle(task.getTitle());
//    }
}
