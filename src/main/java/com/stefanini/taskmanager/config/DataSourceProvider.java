package com.stefanini.taskmanager.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceProvider {

    private final static BasicDataSource mysqlDataSource = new BasicDataSource();

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/config.properties"));

            mysqlDataSource.setUrl(properties.getProperty("url"));
            mysqlDataSource.setUsername(properties.getProperty("user"));
            mysqlDataSource.setPassword(properties.getProperty("password"));
            mysqlDataSource.setMinIdle(4);
            mysqlDataSource.setMaxOpenPreparedStatements(100);

        } catch (FileNotFoundException e) {
            LogManager.getRootLogger().error("No such properties file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMysqlConnection() throws SQLException {
        return  mysqlDataSource.getConnection();
    }
}
