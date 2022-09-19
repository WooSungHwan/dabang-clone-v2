package com.blackdog.dabang.application.user;

import com.blackdog.dabang.domain.user.UserCommand;
import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.domain.user.agent.AgentCommand;
import com.blackdog.dabang.domain.user.agent.AgentCommand.AgentAddCommand;
import com.blackdog.dabang.interfaces.user.dto.AgentDto;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserAgentJoinRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserAgentJoinResponse;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinResponse;
import com.blackdog.dabang.interfaces.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.blackdog.dabang.domain.user.User.UserType.AGENT;
import static com.blackdog.dabang.domain.user.User.UserType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserFacadeTest {

    @Autowired
    private UserFacade facade;
    @Autowired
    private UserMapper mapper;

    @Test
    void join() {
        UserJoinCommand command = UserJoinCommand.builder()
                .name("흑구")
                .id("test")
                .password("test")
                .build();
        UserJoinResponse response = facade.join(command);

        assertThat(response).extracting("seq").isNotNull();
        assertThat(response).extracting("name").isEqualTo(command.getName());
        assertThat(response).extracting("id").isEqualTo(command.getId());
        assertThat(response).extracting("type").isEqualTo(NORMAL);

    }

    @Test
    void agentJoin() {
        UserJoinRequest userJoinRequest = new UserJoinRequest("흑구", "test", "test");
        AgentAddRequest agentAddRequest = new AgentAddRequest("흑구 부동산", "agent-10203910", "02-0000-1111");

        UserJoinCommand userJoinCommand = mapper.toUserJoinCommand(userJoinRequest);
        AgentAddCommand agentAddCommand = mapper.toAgentAddCommand(agentAddRequest);

        UserAgentJoinResponse response = facade.agentJoin(userJoinCommand, agentAddCommand);
        assertThat(response).isNotNull();
        assertThat(response).extracting("user").isNotNull();
        assertThat(response).extracting("user").extracting("seq").isNotNull();
        assertThat(response).extracting("user").extracting("name").isEqualTo(userJoinCommand.getName());
        assertThat(response).extracting("user").extracting("id").isEqualTo(userJoinCommand.getId());
        assertThat(response).extracting("user").extracting("type").isEqualTo(AGENT);
        assertThat(response).extracting("agent").isNotNull();
        assertThat(response).extracting("agent").extracting("id").isNotNull();
        assertThat(response).extracting("agent").extracting("name").isEqualTo(agentAddCommand.getAgentName());
        assertThat(response).extracting("agent").extracting("tel").isEqualTo(agentAddCommand.getTel());
    }
}