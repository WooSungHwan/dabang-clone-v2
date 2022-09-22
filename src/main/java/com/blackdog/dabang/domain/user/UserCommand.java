package com.blackdog.dabang.domain.user;

import com.blackdog.dabang.domain.user.User.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserCommand {

    @Getter
    @Builder
    @ToString
    public static class UserJoinCommand {
        private final String name;
        private final String id;
        private final String password;
        private final UserType userType;

        public User toEntity() {
            return User.builder()
                    .name(name)
                    .userId(id)
                    .password(password)
                    .type(userType)
                    .build();
        }
    }

}
