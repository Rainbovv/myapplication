package application.repositories;

import static org.junit.jupiter.api.Assertions.*;
import application.entities.Task;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskRepositoryTest {

    private static Task task;
    private static TaskRepository taskRepository;

    @BeforeAll
    public static void prepareData(){
        taskRepository = TaskRepository.getInstance();
        task = new Task("TestTitle", "TestDescription");
        taskRepository.removeByTitle(task.getTitle());
    }

    @Order(1)
    @Test
    void create() {
        assertTrue(taskRepository.create(task));
    }

    @Order(2)
    @Test
    void getTaskByTitle() {
        assertEquals(taskRepository.getTaskByTitle(task.getTitle()).getDescription(), task.getDescription());
    }

    @Order(3)
    @Test
    void update() {
        task.setDescription("NewDescription");
        taskRepository.update(task);
        assertEquals(task.getDescription(),
                taskRepository.getTaskByTitle(task.getTitle()).getDescription());
    }

    @Order(4)
    @Test
    void getAllTasks() {
        assertNotEquals(taskRepository.getAllTasks().size(), 0);
    }

    @Order(5)
    @Test
    void removeData() {
        assertTrue(taskRepository.removeByTitle(task.getTitle()));
    }
}