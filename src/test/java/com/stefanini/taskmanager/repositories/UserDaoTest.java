//package com.stefanini.taskmanager.repositories;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.stefanini.taskmanager.dao.factory.DaoFactory;
//import com.stefanini.taskmanager.dao.impl.UserDao;
//import com.stefanini.taskmanager.entities.User;
//import org.junit.jupiter.api.*;
//
//import com.stefanini.taskmanager.dao.hibernate.impl.UserRepository;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class UserDaoTest {
//
//    private static User user;
//    private static UserDao userDao;
//
//    @BeforeAll
//    public static void prepareData(){
//        userDao = DaoFactory.getUserDao();
//        user = new User("testUserEntity", "testUserEntity",
//                "testUserEntity");
//        userDao.removeByUserName(user.getUserName());
//    }
//
//    @Order(1)
//    @Test
//    void create() {
//        assertTrue(userDao.create(user));
//    }
//
//    @Order(2)
//    @Test
//    void getUserByUserName() {
//        assertEquals(userDao.getUserByUserName(user.getUserName()).getFirstName(),
//                     user.getFirstName());
//    }
//
//    @Order(3)
//    @Test
//    void update() {
//        user.setFirstName("newFirstName");
//        userDao.update(user);
//        assertEquals(user.getFirstName(),
//                userDao.getUserByUserName(user.getUserName()).getFirstName());
//    }
//
//    @Order(4)
//    @Test
//    void getAllUsers() {
//        assertNotEquals(userDao.getAll().size(), 0);
//    }
//
//    @Order(5)
//    @Test
//    void removeData() {
//        assertTrue(userDao.remove(user));
//    }
//}
