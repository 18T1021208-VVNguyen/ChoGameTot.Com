package com.example.startappdemo;

import com.example.startappdemo.repository.UserOnlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StartAppDeMoApplication {

    public static void main(String[] args) {

        SpringApplication.run(StartAppDeMoApplication.class, args);

    }

}
