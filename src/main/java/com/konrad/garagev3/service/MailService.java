package com.konrad.garagev3.service;

import com.konrad.garagev3.model.AnonymousUserQuestion;
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

    public String sendEmail(AnonymousUserQuestion anonymousUserQuestion){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(anonymousUserQuestion.getMail());
        message.setSubject(anonymousUserQuestion.getName());
        message.setText(anonymousUserQuestion.getMessage());
        this.emailSender.send(message);
        return "EmailSuccess";
    }
}
