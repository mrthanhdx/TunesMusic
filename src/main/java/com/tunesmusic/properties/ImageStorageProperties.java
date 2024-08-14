package com.tunesmusic.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "file.image")
@Component
public class ImageStorageProperties {
    private String uploadDir = "src/main/resources/static/img/songImage";

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}