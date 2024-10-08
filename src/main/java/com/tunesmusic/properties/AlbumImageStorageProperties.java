package com.tunesmusic.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@ConfigurationProperties(prefix = "file.albumimage")
@Component
public class AlbumImageStorageProperties {


        private String uploadDir = "src/main/resources/static/img/album";

        public String getUploadDir() {
            return uploadDir;
        }

        public void setUploadDir(String uploadDir) {
            this.uploadDir = uploadDir;
        }
    }
