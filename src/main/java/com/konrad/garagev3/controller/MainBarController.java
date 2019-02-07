package com.konrad.garagev3.controller;

import com.konrad.garagev3.mail.AnonymousUserQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainBarController {
    private final JavaMailSender emailSender;

    @Autowired
    public MainBarController(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }

    @RequestMapping(value = "/")
    public String isitingCard() {
        return "visitingCard";
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/contact")
    public String contact(Model model) {
        model.addAttribute("mailData", new AnonymousUserQuestion());
        return "contact";
    }

    @PostMapping(value = "/contact")
    public String contact2(Model model, @ModelAttribute("mailData") AnonymousUserQuestion anonymousUserQuestion) {
        System.out.println(anonymousUserQuestion.getMail());
        sendEmail(anonymousUserQuestion);
        return "contact";
    }

    private String sendEmail(AnonymousUserQuestion anonymousUserQuestion){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(anonymousUserQuestion.getMail());
        message.setSubject("Welcome on board login");
        message.setText("http://localhost:3000/token/");
        this.emailSender.send(message);
        return "EmailSuccess";
    }

    @RequestMapping(value = "/services")
    public String services() {
        return "services";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/blog")
    public String blog() {
        return "blog";
    }
}
