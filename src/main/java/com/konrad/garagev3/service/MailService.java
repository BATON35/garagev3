package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.AnonymousUserQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender emailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }

    // TODO: 11.07.2019 jak napisac (czy nalezy pisac) test dla tej metody;
    public String sendEmail(AnonymousUserQuestion anonymousUserQuestion) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(anonymousUserQuestion.getMail());
        message.setSubject(anonymousUserQuestion.getName());
        message.setText(anonymousUserQuestion.getMessage());
        this.emailSender.send(message);
        return "EmailSuccess";
    }
}
