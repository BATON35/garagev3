package com.konrad.garagev3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainBarController {
    @RequestMapping(value = "/")
    public String sss(){
        return "visitingCard";
    }

    @RequestMapping(value = "/about")
    public String about(){
        return "about";
    }

    @RequestMapping(value = "/contact")
    public String contact(){
        return "contact";
    }

    @RequestMapping(value = "/services")
    public String blog(){
        return "services";
    }

    @RequestMapping("/index")
    public String getIndex() {
        return "index";
    }
    @RequestMapping("/blog")
    public String getBlog() {
        return "blog";
    }
}
