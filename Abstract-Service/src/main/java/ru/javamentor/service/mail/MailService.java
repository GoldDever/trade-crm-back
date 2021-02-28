package ru.javamentor.service.mail;

public interface MailService {

    void send(String to, String subject, String message);
}
