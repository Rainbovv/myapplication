package com.stefanini.taskmanager.entities;

import com.stefanini.taskmanager.util.annotation.NotifyWhenPersisting;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@NotifyWhenPersisting(entityType = NotifyWhenPersisting.entityType.USER)
public class User extends AbstractEntity {

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "user_name", unique = true, length = 50, nullable = false)
    private String userName;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_task",
            uniqueConstraints = @UniqueConstraint(columnNames={"user_id", "task_id"}),
            inverseJoinColumns = @JoinColumn(name = "task_id",
                    nullable = false),
            joinColumns = @JoinColumn(name = "user_id",
                    nullable = false))
    private List<Task> tasks = new ArrayList<>();

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
