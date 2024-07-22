package com.tunesmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String showLoginForm() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        // Handle the login logic here
//        // For simplicity, we will just print the username and password to the console
//        Syst  em.out.println("Username: " + username);
//        System.out.println("Password: " + password);
//        // Redirect to a home page or dashboard after successful login
//        return "redirect:/home";
//    }
}
