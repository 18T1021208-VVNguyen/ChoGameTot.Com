package com.example.startappdemo.controller;

import com.example.startappdemo.model.LoginUserModel;
import com.example.startappdemo.model.RegisterUserModel;
import com.example.startappdemo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/login" )
    public  String login(HttpServletRequest request , Model model){
        if(request.getParameter("message") != null ){


            if(request.getParameter("message").equals("error_login")){
                HttpSession session =  request.getSession(false);
                if(session != null){
                    String messError =  ((Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage();
                    model.addAttribute("messError",messError + "/\n thong tin tk ko chinh xac");
                }

            }

            if(request.getParameter("message").equals("required_login")){
                model.addAttribute("messError",  " TRang này yêu cầu phải login");
            }

        }
        return "login";
    }



    @GetMapping("/register")
    public  String register(Model model){
        model.addAttribute("user",new RegisterUserModel());
        return "register";
    }

    @PostMapping("/register")
    public  String register(@ModelAttribute("user") @Valid RegisterUserModel request , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors()){
            if(bindingResult.hasGlobalErrors()){
                bindingResult.rejectValue( "retypePassword" ,"error.retypePassword",bindingResult.getGlobalError().getDefaultMessage());
            }
            return "register";
        }
        userService.saveUser(request);
        model.addAttribute("status" , "Dang ki thanh cong !");
        return "redirect:/auth/login";
    }

    @GetMapping("/403")
    public String accessDenied(){

        return "403Page";
    }
}
