package com.konrad.garagev3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/web-socket")
public class NotificationController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping
    @MessageMapping("/test")
    public String test(@RequestParam String text) {
        System.out.println(text + "\n\n\n");
        simpMessagingTemplate.convertAndSend("queue/test", text);
        return text;
    }
}
