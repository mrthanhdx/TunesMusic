package com.tunesmusic;

import com.tunesmusic.properties.AudioStorageProperties;
import com.tunesmusic.properties.ImageStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties({ImageStorageProperties.class, AudioStorageProperties.class})
public class TunesMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(TunesMusicApplication.class, args);
    }
}
