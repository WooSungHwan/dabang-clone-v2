package com.blackdog.dabang;

import com.blackdog.dabang.config.jwt.JwtProperties;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({JwtProperties.class})
@SpringBootApplication
public class DabangApplication {

    // 예제에 대한 집중을 위해 time zone 고려를 하지 않겠다. -> 한국시간으로 고정.
    @PostConstruct
    private void postConstruct() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(DabangApplication.class, args);
    }

}
