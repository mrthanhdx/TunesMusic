package com.tunesmusic.controller.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testpassword {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("thanh123"));
    }
}
