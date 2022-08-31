package com.blackdog.dabang.config.jwt;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;


@Getter
@AllArgsConstructor
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "dabang.jwt")
public class JwtProperties {
    @NotBlank(message = "JWT 인증키를 설정해주세요.")
    private final String secret;

    @Min(value = 1, message = "JWT 인증 만료 밀리초 값을 {value}보다 크게 설정해주세요.")
    private final Long expiration;

}
