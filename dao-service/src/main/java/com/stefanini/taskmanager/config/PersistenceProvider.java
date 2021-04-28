package com.stefanini.taskmanager.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceProvider {

    private static String mySQLPersistenceProvider = "local-mysql";

    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(mySQLPersistenceProvider);
    }

    public static void setMySQLPersistenceProvider(String mySQLPersistenceProvider) {
        PersistenceProvider.mySQLPersistenceProvider = mySQLPersistenceProvider;
    }
}
