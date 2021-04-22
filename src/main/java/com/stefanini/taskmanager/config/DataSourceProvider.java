package com.stefanini.taskmanager.config;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceProvider {

    private final static BasicDataSource mysqlDataSource = new BasicDataSource();
    static {
        mysqlDataSource.setUrl("jdbc:mysql://localhost/myapplication");
        mysqlDataSource.setUsername("root");
        mysqlDataSource.setPassword("Rainb0vv@446571");
        mysqlDataSource.setMinIdle(4);
        mysqlDataSource.setMaxOpenPreparedStatements(100);
    }

    public static Connection getMysqlConnection() throws SQLException {
        return  mysqlDataSource.getConnection();
    }
}
