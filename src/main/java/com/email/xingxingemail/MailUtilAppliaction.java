package com.email.xingxingemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MailUtilAppliaction {

    public static void main(String[] args) {

        SpringApplication.run(MailUtilAppliaction.class, args);
    }
}
