package com.stefanini.taskmanager.util.sender.impl;

import com.stefanini.taskmanager.config.SMTPSessionProvider;
import com.stefanini.taskmanager.util.sender.Sender;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender implements Sender {

    private String recipient;
    private final Session session;


    public EmailSender(String recipient) {
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
            e.printStackTrace();
        }
        return true;
    }
}
