package com.example.demo.controller;

import com.example.demo.dto.DocumentEdit;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/edit")
    @SendTo("/topic/messages")
    public DocumentEdit handleEdit(DocumentEdit edit) {
        return edit; // echo back to subscribers
    }

}