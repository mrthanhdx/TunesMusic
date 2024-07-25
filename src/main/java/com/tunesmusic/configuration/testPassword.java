package com.tunesmusic.configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.beans.Encoder;

public class testPassword {
    public static void main(String[] args) {
        System.out.println( new BCryptPasswordEncoder().encode("thanh123"));
    }
}
