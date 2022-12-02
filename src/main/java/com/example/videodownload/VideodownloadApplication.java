package com.example.videodownload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class VideodownloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideodownloadApplication.class, args);
    }

}
