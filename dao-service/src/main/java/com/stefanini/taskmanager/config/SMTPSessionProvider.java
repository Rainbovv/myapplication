package com.stefanini.taskmanager.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.util.Properties;

public class SMTPSessionProvider {

    private final static Logger logger = LogManager.getRootLogger();
    private final static Properties prop = new Properties();

    public static Session getSession() {

        try {
            prop.load(ClassLoader.getSystemResourceAsStream("smtp.properties"));
        } catch (IOException e) {
            logger.error(e.getMessage());

        }

        return Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(prop.getProperty("sender"),
                                prop.getProperty("password"));
                    }
        });
    }
}
