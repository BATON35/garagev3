package com.konrad.garagev3.controller;

import com.konrad.garagev3.mail.AnonymousUserQuestion;
import com.konrad.garagev3.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainBarController {
    private final MailService mailService;

    @Autowired
    public MainBarController(MailService mailService) {
        this.mailService = mailService;
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
        mailService.sendEmail(anonymousUserQuestion);
        return "contact";
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
