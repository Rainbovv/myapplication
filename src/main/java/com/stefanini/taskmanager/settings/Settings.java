package com.stefanini.taskmanager.settings;

public class Settings {

    private static String persistenceProviderName = "local-mysql";

    public static String getPersistenceProviderName() {
        return persistenceProviderName;
    }
}
