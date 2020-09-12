package com.konrad.garagev3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @PreAuthorize("isAuthenticated()")
    @SendTo("/topic/test")
    public String test(Message<String> string, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println(string);
        return string.getPayload() + "hello";
    }

    @GetMapping("/web-socket")
    public void testRest(@RequestParam String string) {
        simpMessagingTemplate.convertAndSend("/topic/test", string);
    }

    @GetMapping("/web-socket-user")
    public void testUser(@RequestParam String string, @RequestParam String id) {
        simpMessagingTemplate.convertAndSend( "/topic/notify/" + id, string);
    }
}
