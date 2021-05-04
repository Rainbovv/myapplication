package com.stefanini.taskmanager.util.sender.impl;

import com.stefanini.taskmanager.config.SMTPSessionProvider;
import com.stefanini.taskmanager.util.sender.Sender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender implements Sender {

    private Logger logger;
    private String recipient;
    private final Session session;


    public EmailSender(String recipient) {
        logger = LogManager.getRootLogger();
        session = SMTPSessionProvider.getSession();
        this.recipient = recipient;
    }

    @Override
    public boolean send(String subject, String message) {
        try {
            MimeMessage mm = new MimeMessage(session);
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            mm.setSubject(subject);
            mm.setText(message);
            Transport.send(mm);
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
        return true;
    }
}
