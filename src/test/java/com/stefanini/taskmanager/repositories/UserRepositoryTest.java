package com.stefanini.taskmanager.repositories;
import static org.junit.jupiter.api.Assertions.*;

import com.stefanini.taskmanager.entities.User;
import org.junit.jupiter.api.*;

import com.stefanini.taskmanager.dao.UserRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    private static User user;
    private static UserRepository userRepository;

    @BeforeAll
    public static void prepareData(){
        userRepository = UserRepository.getInstance();
        user = new User("testUserEntity", "testUserEntity",
                "testUserEntity");
        userRepository.removeByUserName(user.getUserName());
    }

    @Order(1)
    @Test
    void create() {
        assertTrue(userRepository.create(user));
    }

    @Order(2)
    @Test
    void getUserByUserName() {
        assertEquals(userRepository.getUserByUserName(user.getUserName()).getFirstName(),
                     user.getFirstName());
    }

    @Order(3)
    @Test
    void update() {
        user.setFirstName("newFirstName");
        userRepository.update(user);
        assertEquals(user.getFirstName(),
                userRepository.getUserByUserName(user.getUserName()).getFirstName());
    }

    @Order(4)
    @Test
    void getAllUsers() {
        assertNotEquals(userRepository.getAllUsers().size(), 0);
    }

    @Order(5)
    @Test
    void removeData() {
        assertTrue(userRepository.remove(user));
    }
}
