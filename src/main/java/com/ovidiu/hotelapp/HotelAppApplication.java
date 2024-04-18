package com.ovidiu.hotelapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class HotelAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelAppApplication.class, args);
    }

}
