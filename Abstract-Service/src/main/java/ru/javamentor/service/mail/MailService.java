package ru.javamentor.service.mail;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {

    void send(String to, String subject, String message);
    void sendByTemplate(SimpleMailMessage template);
}
