package com.vuelogix.recordingmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling

public class RecordingManagerApplication {

    public static void main(String[] args) {
        System.out.println("Starting recording manager");
        SpringApplication.run(RecordingManagerApplication.class, args);
    }
}
