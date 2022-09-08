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

        public User toNormalUserEntity() {
            return User.builder()
                    .name(name)
                    .id(id)
                    .password(password)
                    .type(UserType.NORMAL)
                    .build();
        }

        public User toAgentUserEntity() {
            return User.builder()
                       .name(name)
                       .id(id)
                       .password(password)
                       .type(UserType.AGENT)
                       .build();
        }
    }

}
