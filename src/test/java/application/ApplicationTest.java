package application;

import application.entities.Task;
import application.entities.User;
import application.repositories.TaskRepository;
import application.repositories.UserRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTest {

    private static UserRepository userRepository;
    private static TaskRepository taskRepository;
    private static User user;
    private static Task task;


    @BeforeAll
    public static void prepareData() {
        userRepository = UserRepository.getInstance();
        taskRepository = TaskRepository.getInstance();
        user = new User("TestFirstName", "TestLastName", "TestUserName" );
        task = new Task("TestTitle", "TestDescription");
        userRepository.removeByUserName(user.getUserName());
        taskRepository.removeByTitle(task.getTitle());
    }

    @Order(1)
    @Test
    void mainCreateUser() {
        String[] args = new String[]{"-createUser", "-fn=" + user.getFirstName(),
                "-ln=" + user.getLastName(), "-un=" + user.getUserName()};
        Application.main(args);
        assertNotNull(userRepository.getUserByUserName(user.getUserName()));
    }

    @Order(2)
    @Test
    void mainAddTask() {
        String[] args = new String[]{"-addTask", "-un=" + user.getUserName(),
                "-tt=" + task.getTitle(), "-td=" + task.getDescription()};
        Application.main(args);
        assertNotEquals(userRepository.getUserByUserName("TestUserName").getTasks().size(), 0);
    }

    @AfterAll
    static void removeData() {
        userRepository.removeByUserName(user.getUserName());
        taskRepository.removeByTitle(task.getTitle());
    }
}