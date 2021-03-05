package ru.javamentor.configuration.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class MailTemplates {

    @Bean(name = "newUser")
    public SimpleMailMessage templateMessageNewUser() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Регистрация в Trade-CRM");
        message.setText(
                "Вы зарегистрированы в Trade-CRM\n" +
                "Ваш временный пароль: %s\n" +
                "Данный пароль необходимо сменить.");

        return message;
    }
}
