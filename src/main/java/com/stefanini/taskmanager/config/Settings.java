package com.stefanini.taskmanager.config;

public class Settings {

    private static String mySQLPersistenceProvider = "local-mysql";

    public static String getMySQLPersistenceProvider() {
        return mySQLPersistenceProvider;
    }
}
