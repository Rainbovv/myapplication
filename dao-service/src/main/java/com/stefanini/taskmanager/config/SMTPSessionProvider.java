package com.stefanini.taskmanager.config;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.util.Properties;

public class SMTPSessionProvider {

    private final static Properties prop = new Properties();

    public static Session getSession() {
        try {
            prop.load(ClassLoader.getSystemResourceAsStream("smtp.properties"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(prop.getProperty("sender"),
                                prop.getProperty("password"));
                    }
        });
    }
}
