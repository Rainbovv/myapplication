package application;

import application.entities.Task;
import application.entities.User;
import application.repositories.UserRepository;

public class Application {
    public static void main(String[] args) {

        UserRepository userRepository = UserRepository.getInstance();

//        userRepository.create();


        User user = new User("Vasea", "Pupkin", "Vpupkin");
//                userRepository.getUserByName("Vpupkin");

        user.addTask(new Task("to do", "Do it quickly"));

        userRepository.create(user);

        System.out.println(userRepository.getUserByName("Vpupkin"));
    }
}
