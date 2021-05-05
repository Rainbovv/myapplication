//package com.stefanini.taskmanager.service.agent;
//
//import com.stefanini.taskmanager.entities.Task;
//import com.stefanini.taskmanager.entities.User;
//import com.stefanini.taskmanager.util.annotation.NotifyWhenPersisting;
//import com.stefanini.taskmanager.util.sender.Sender;
//import com.stefanini.taskmanager.util.sender.impl.EmailSender;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//
//@Aspect
//public class NotificationAgent {
//
//    Sender sender = new EmailSender("radu.turcanu@extendaretail.com");
//
//    @Around("within(com.stefanini.taskmanager.service.impl.UserServiceImpl)")
//    public Object notifyTaskAdding(ProceedingJoinPoint pjp) throws Throwable {
//
//        Object retValue;
//
//        switch (pjp.toString()) {
//            case "call(AbstractEntity com.stefanini.taskmanager.dao.UserDao.create(AbstractEntity))":
//
//                retValue = pjp.proceed();
//
//                if (retValue.getClass().isAnnotationPresent(NotifyWhenPersisting.class))
//                    notifyUserCreating((User)retValue);
//                break;
//
//            case "execution(boolean " +
//                    "com.stefanini.taskmanager.service.impl.UserServiceImpl.addTask(Task, String))":
//
//                Task task = (Task)pjp.getArgs()[0];
//                String userName = (String)pjp.getArgs()[1];
//
//                retValue = pjp.proceed();
//
//                if ((boolean) retValue &&
//                        task.getClass().isAnnotationPresent(NotifyWhenPersisting.class))
//                    notifyTaskAssigning(task, userName);
//                break;
//
//            case "execution(User com.stefanini.taskmanager.service.impl.UserServiceImpl" +
//                    ".createUserAndAddTask(User, Task))":
//                userName = ((User)pjp.getArgs()[0]).getUserName();
//                task = (Task)pjp.getArgs()[1];
//
//                retValue = pjp.proceed();
//
//                if (retValue != null && retValue.getClass()
//                        .isAnnotationPresent(NotifyWhenPersisting.class))
//                    notifyTaskAssigning(task, userName);
//                break;
//
//            default:
//                retValue = pjp.proceed();
//        }
//        return retValue;
//    }
//
//    private void notifyUserCreating(User user) {
//        String message = String.format("User %s / %s identified by %s has been created!",
//                user.getFirstName(), user.getLastName(), user.getUserName());
//
//        sender.send("New user created!", message);
//    }
//
//    private void notifyTaskAssigning(Task task, String userName) {
//        String message = String.format("Task %s has been assigned to %s!",
//                task.getTitle(), userName);
//
//        sender.send("Task assigned!", message);
//    }
//}
