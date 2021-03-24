package application.entities;

import javax.persistence.*;

@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(length = 50)
    private String title;

    private String description;

    public Task() {}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
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
