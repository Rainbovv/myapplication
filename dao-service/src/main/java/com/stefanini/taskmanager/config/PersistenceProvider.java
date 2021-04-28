package com.stefanini.taskmanager.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceProvider {

    private static String persistenceUnitName = "local-mysql";

    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public static void setPersistenceUnitName(String persistenceUnitName) {
        PersistenceProvider.persistenceUnitName = persistenceUnitName;
    }
}
