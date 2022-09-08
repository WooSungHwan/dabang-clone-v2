package com.blackdog.dabang.interfaces.user.dto;

import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddRequest;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddResponse;
import java.util.stream.Stream;
import javax.validation.Valid;
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
        @NotEmpty(message = "이름을 입력해주세요.")
        private String name;

        @NotEmpty(message = "아이디를 입력해주세요.")
        private String id;

        @NotEmpty(message = "비밀번호를 입력해주세요.")
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

        public UserJoinResponse(User user) {
            this.seq = user.getSeq();
            this.name = user.getName();
            this.id = user.getId();
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
}
