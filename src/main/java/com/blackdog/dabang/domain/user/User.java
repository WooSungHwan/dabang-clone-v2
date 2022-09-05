package com.blackdog.dabang.domain.user;

import com.blackdog.dabang.common.exception.InvalidParameterException;
import java.util.function.Supplier;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name = "id", length = 50)
    private String id;

    @Column(name = "password", columnDefinition = "text")
    private String password;

    @Builder
    public User(String name, String id, String password) {
        if (StringUtils.isBlank(name)) throw new InvalidParameterException("User.name");
        if (StringUtils.isBlank(id)) throw new InvalidParameterException("User.id");
        if (StringUtils.isBlank(password)) throw new InvalidParameterException("User.password");

        this.name = name;
        this.id = id;
        this.password = password;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

}
