package com.example.demo.controller;

import com.example.demo.dto.DocumentEdit;

@Controller
public class ChatController {
    @MessageMapping("/edit")
    @SendTo("/topic/messages")
    public DocumentEdit handleEdit(DocumentEdit edit) {
        return edit; // echo back to subscribers
    }

}