package com.batch.job;

import com.batch.job.builder.MailContentBuilder;
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

    @Autowired
    private MailContentBuilder mailContentBuilder;

    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class);
    }

    @Bean
    public boolean sendMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String text = mailContentBuilder.build("MailTemplate", "Some message");
        simpleMailMessage.setTo("some@some.com");
        simpleMailMessage.setCc("some1@some.com");
        simpleMailMessage.setFrom("amie@some.com");
        simpleMailMessage.setSubject("Test email");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
        return true;
    }
}
