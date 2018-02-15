package com.batch.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class StarterApplication {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class);
    }

    public boolean sendMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("some@some.com");
        simpleMailMessage.setCc("some1@some.com");
        simpleMailMessage.setFrom("amie@some.com");
        simpleMailMessage.setSubject("Test email");
        simpleMailMessage.setText("Text message");
        javaMailSender.send(simpleMailMessage);
        return true;
    }
}
