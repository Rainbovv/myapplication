package com.stefanini.taskmanager.util.sender;

public interface Sender {
    boolean send(String subject, String message);
}
