package com.blackdog.dabang.domain.user.agent;

import com.blackdog.dabang.domain.user.UserCommand;
import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.domain.user.UserService;
import com.blackdog.dabang.domain.user.agent.AgentCommand.AgentAddCommand;
import com.blackdog.dabang.interfaces.user.dto.AgentDto;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddRequest;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddResponse;
import com.blackdog.dabang.interfaces.user.dto.UserDto;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinResponse;
import com.blackdog.dabang.interfaces.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class AgentServiceImplTest {

    @Autowired
    private AgentService service;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void 부동산생성() {
        UserJoinRequest userJoinRequest = new UserJoinRequest("흑구", "test", "test");
        AgentAddRequest agentAddRequest = new AgentAddRequest("흑구 부동산", "agent-10203910", "02-0000-1111");

        UserJoinCommand userJoinCommand = userMapper.toUserJoinCommand(userJoinRequest);
        AgentAddCommand agentAddCommand = userMapper.toAgentAddCommand(agentAddRequest);

        UserJoinResponse userJoinResponse = userService.join(userJoinCommand);

        AgentAddResponse agentResponse = service.addAgent(userJoinResponse.getSeq(), agentAddCommand);
        assertThat(agentResponse).isNotNull();
        assertThat(agentResponse).extracting("id").isNotNull();
        assertThat(agentResponse).extracting("name").isEqualTo(agentAddCommand.getAgentName());
        assertThat(agentResponse).extracting("tel").isEqualTo(agentAddCommand.getTel());
    }

}
