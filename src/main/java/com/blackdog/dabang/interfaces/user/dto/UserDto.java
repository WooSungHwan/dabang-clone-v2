package com.blackdog.dabang.interfaces.user.dto;

import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.User.UserType;
import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddRequest;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddResponse;
import java.util.stream.Stream;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

public class UserDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class UserJoinRequest {
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @NotBlank(message = "아이디를 입력해주세요.")
        private String id;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        public UserJoinCommand toCommand() {
            return UserJoinCommand.builder()
                    .id(id)
                    .password(password)
                    .name(name)
                    .build();
        }
    }

    @Getter
    public static class UserJoinResponse {
        private Long seq;

        private String name;

        private String id;

        private UserType type;

        public UserJoinResponse(User user) {
            this.seq = user.getSeq();
            this.name = user.getName();
            this.id = user.getUserId();
            this.type = user.getType();
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class UserAgentJoinRequest {
        @Valid
        private UserJoinRequest userJoinRequest;

        @Valid
        private AgentAddRequest agentAddRequest;
    }

    @Value(staticConstructor = "of")
    public static class UserAgentJoinResponse {
        private UserJoinResponse user;

        private AgentAddResponse agent;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class UserLoginRequest {
        @NotBlank(message = "아이디를 입력해주세요.")
        private String id;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    @Value(staticConstructor = "of")
    public static class UserLoginResponse {
        private String token;
    }

    @Getter
    public static class UserResponse {
        private String id;

        private Long seq;

        private String name;

        private UserType type;

        public UserResponse(User user) {
            this.seq = user.getSeq();
            this.name = user.getName();
            this.id = user.getUserId();
            this.type = user.getType();
        }
    }
}
