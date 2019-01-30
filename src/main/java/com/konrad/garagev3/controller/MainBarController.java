package com.konrad.garagev3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainBarController {
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/about")
    public String about(){
        return "about";
    }

    @RequestMapping(value = "/contact.html")
    public String contact(){
        return "contact";
    }

    @PostMapping(value = "/blog")
    public String blog(){
        return "blog";
    }

    @RequestMapping("/index")
    public String getIndex() {
        return "index";
    }
}
