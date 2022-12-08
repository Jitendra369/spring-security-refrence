package com.springboot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class WebController {
    
    // user landing page
    @GetMapping("/home")
    public String getHome(){
        return "userHome";
    }

    // login page
    @GetMapping("/login")
    public String getLoginPage(){
        return "loginPage";
    }
}
