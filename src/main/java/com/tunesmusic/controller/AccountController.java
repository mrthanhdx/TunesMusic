package com.tunesmusic.controller;

import com.tunesmusic.model.Role;
import com.tunesmusic.model.User;
import com.tunesmusic.repository.RoleRepository;
import com.tunesmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @GetMapping("/sign-up")
    public String showSignUpForm(){
        return "sign-up";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    @PostMapping("/sign-up")
    public String createNewUser(User user, @RequestParam("password") String passwordString) {
        String password = new BCryptPasswordEncoder().encode(passwordString);
        user.setPassword(password);
        user.setAccountLevel("Normal");
        user.setEnable(true);
        userService.save(user);
        User user1 = userService.findUserByUsername(user.getUsername());
        userService.insertDefaultUserRole(user1.getId());

        return "login";
    }
}
