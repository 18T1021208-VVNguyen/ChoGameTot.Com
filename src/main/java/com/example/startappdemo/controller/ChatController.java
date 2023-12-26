package com.example.startappdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {


    @GetMapping("/chat")
    public String chatMessage(){
        return "chat";
    }

}
