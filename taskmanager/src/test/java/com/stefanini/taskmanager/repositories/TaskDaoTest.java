package com.stefanini.taskmanager.repositories;//package com.stefanini.com.stefanini.taskmanager.repositories;
//
//import org.junit.jupiter.api.*;
//
//import com.stefanini.com.stefanini.taskmanager.dao.hibernate.impl.TaskRepository;
//import com.stefanini.com.stefanini.taskmanager.entities.Task;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class TaskDaoTest {
//
//    private static Task task;
//    private static TaskDao taskDao;
//
//    @BeforeAll
//    public static void prepareData(){
//        taskDao = DaoFactory.getTaskDao();
//        task = new Task("TestTitle", "TestDescription");
//        taskDao.removeByTitle(task.getTitle());
//    }
//
//    @Order(1)
//    @Test
//    void create() {
//        assertTrue(taskDao.create(task));
//    }
//
//    @Order(2)
//    @Test
//    void getTaskByTitle() {
//        assertEquals(taskDao.getTaskByTitle(task.getTitle()).getDescription(),
//                     task.getDescription());
//    }
//
//    @Order(3)
//    @Test
//    void update() {
//        task.setDescription("NewDescription");
//        taskDao.update(task);
//        assertEquals(task.getDescription(),
//                taskDao.getTaskByTitle(task.getTitle()).getDescription());
//    }
//
//    @Order(4)
//    @Test
//    void getAllTasks() {
//        assertNotEquals(taskDao.getAll().size(), 0);
//    }
//
//    @Order(5)
//    @Test
//    void removeData() {
//        assertTrue(taskDao.removeByTitle(task.getTitle()));
//    }
//}
