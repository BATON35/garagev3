package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.AnonymousUserQuestion;
import com.konrad.garagev3.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainBarController {
    private final MailService mailService;

    @Autowired
    public MainBarController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    public String visitingCard() {
        return "visitingCard";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("mailData", new AnonymousUserQuestion());
        return "contact";
    }

    @PostMapping("/contact")
    public String contact2(Model model, @ModelAttribute("mailData") AnonymousUserQuestion anonymousUserQuestion) {
        mailService.sendEmail(anonymousUserQuestion);
        return "contact";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }


}
