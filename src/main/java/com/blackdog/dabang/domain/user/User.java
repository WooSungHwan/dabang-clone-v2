package com.blackdog.dabang.domain.user;

import static javax.persistence.EnumType.STRING;

import com.blackdog.dabang.common.exception.InvalidParameterException;
import java.util.function.Supplier;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "password", columnDefinition = "text")
    private String password;

    @Enumerated(STRING)
    @Column(name = "type", length = 10)
    private UserType type;

    @Builder
    public User(String name, String userId, String password, UserType type) {
        if (StringUtils.isBlank(name)) throw new InvalidParameterException("User.name");
        if (StringUtils.isBlank(userId)) throw new InvalidParameterException("User.userId");
        if (StringUtils.isBlank(password)) throw new InvalidParameterException("User.password");

        this.name = name;
        this.userId = userId;
        this.password = password;
        this.type = type;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    @Getter
    @AllArgsConstructor
    public enum UserType {

        NORMAL("일반유저"),
        AGENT("부동산유저");

        private String name;

    }
}
