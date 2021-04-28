package com.stefanini.taskmanager.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "task")
public class Task extends AbstractEntity {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_task",
            uniqueConstraints = @UniqueConstraint(columnNames={"user_id", "task_id"}),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    nullable = false),
            joinColumns = @JoinColumn(name = "task_id",
                    nullable = false))
    private Set<User> users;

    @Column(length = 50, unique = true)
    private String title;

    private String description;

    public Task() {}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
