package application;

import application.entities.Task;
import application.entities.User;
import application.repositories.UserRepository;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import java.util.Arrays;

public class Application {

    static UserRepository userRepository = UserRepository.getInstance();

    public static void main(String[] args) {

        OptionParser parser = new OptionParser();
        parser.acceptsAll(Arrays.asList("createUser", "showTasks", "addTask", "showAllUsers"))
                .withOptionalArg();

        if (args.length == 0)
            return;

        switch (args[0]) {
            case "-createUser":
                parser.accepts("ln").requiredIf("createUser")
                        .withRequiredArg();
                parser.accepts("fn").requiredIf("createUser")
                        .withRequiredArg();
                parser.accepts("un").requiredIf("createUser",
                        "showTasks", "addTask").withRequiredArg();

                createUser(parser.parse(args));
                break;

            case "-showAllUsers":
                userRepository.getAllUsers().forEach(System.out::println);
                break;

            case "-addTask":
                parser.accepts("tt").requiredIf("addTask").withRequiredArg();
                parser.accepts("td").requiredIf("addTask").withRequiredArg();
                parser.accepts("un").requiredIf("createUser",
                        "addTask").withRequiredArg();

                addTask(parser.parse(args));
                break;

            case "-showTasks":
                parser.accepts("un").requiredIf("createUser",
                        "showTasks").withRequiredArg();

                userRepository.getUserByUserName(parser.parse(args).valueOf("un")
                        .toString()).getTasks().forEach(System.out::println);
        }
    }

    public static void addTask(OptionSet options) {
        User user = userRepository.getUserByUserName(options.valueOf("un").toString());
        Task task = new Task(options.valueOf("tt").toString(),
                options.valueOf("td").toString());
        user.getTasks().add(task);

        userRepository.update(user);
    }

    public static void createUser(OptionSet options) {
        User user = new User(options.valueOf("fn").toString(),
                options.valueOf("ln").toString(),
                options.valueOf("un").toString());

        userRepository.create(user);
    }
}
