package com.inv.invmgmt.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

   @RequestMapping("/login")
    public String loginPage(){return "security/login";}

    @GetMapping("/register")
    public String register(){return "security/register";}

    @RequestMapping("/index")
    public String homePage(){return "index";}
}
