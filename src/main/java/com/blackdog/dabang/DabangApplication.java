package com.blackdog.dabang;

import com.blackdog.dabang.config.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({JwtProperties.class})
@SpringBootApplication
public class DabangApplication {

    public static void main(String[] args) {
        SpringApplication.run(DabangApplication.class, args);
    }

}
