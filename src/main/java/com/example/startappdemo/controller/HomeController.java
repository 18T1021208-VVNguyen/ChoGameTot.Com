package com.example.startappdemo.controller;

import com.example.startappdemo.security.service.impl.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping({"/index" , "/"})
    public String home(){
        return "index";
    }

    @GetMapping("/user-infor")
    public  String userInfor( Principal principal , Model model){
        UserDetailsImpl u =  (UserDetailsImpl) ((Authentication) principal).getPrincipal();
       model.addAttribute("user",u);
        return "user-infor";
    }

}
