package com.blackdog.dabang.domain.user;

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

        public User toEntity() {
            return User.builder()
                    .name(name)
                    .id(id)
                    .password(password)
                    .build();
        }

    }

}
