package com.stefanini.taskmanager.entities;

public class UserTaskRelation extends AbstractEntity {

    private Long userId;
    private Long taskId;

    public UserTaskRelation(Long userId, Long taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public UserTaskRelation() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "UserTaskRelation{" +
                "userId=" + userId +
                ", taskId=" + taskId +
                '}';
    }
}