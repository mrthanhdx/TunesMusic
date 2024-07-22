package com.tunesmusic.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/admin/tunesmusic")
public class AdminController {

    @GetMapping("")
    public String openAdminPage(){
        return "/admin/quantri";
    }
}
