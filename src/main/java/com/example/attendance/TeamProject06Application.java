package com.example.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.attendance.entity")
public class TeamProject06Application {

    public static void main(String[] args) {
        SpringApplication.run(TeamProject06Application.class, args);
    }
}
